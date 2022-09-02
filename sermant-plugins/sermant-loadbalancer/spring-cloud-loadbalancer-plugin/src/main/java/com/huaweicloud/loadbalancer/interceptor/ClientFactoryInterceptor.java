/*
 * Copyright (C) 2022-2022 Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.huaweicloud.loadbalancer.interceptor;

import com.huaweicloud.loadbalancer.cache.SpringLoadbalancerCache;
import com.huaweicloud.loadbalancer.config.LbContext;
import com.huaweicloud.loadbalancer.config.LoadbalancerConfig;
import com.huaweicloud.loadbalancer.config.SpringLoadbalancerType;
import com.huaweicloud.loadbalancer.rule.RuleManager;
import com.huaweicloud.sermant.core.common.LoggerFactory;
import com.huaweicloud.sermant.core.plugin.agent.entity.ExecuteContext;
import com.huaweicloud.sermant.core.plugin.agent.interceptor.AbstractInterceptor;
import com.huaweicloud.sermant.core.plugin.config.PluginConfigManager;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * LoadBalancerClientFactory增强类-针对spring loadbalancer
 *
 * @author provenceee
 * @since 2022-01-20
 */
public class ClientFactoryInterceptor extends AbstractInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger();

    private final LoadbalancerConfig config;

    /**
     * 构造方法
     */
    public ClientFactoryInterceptor() {
        config = PluginConfigManager.getPluginConfig(LoadbalancerConfig.class);
    }

    @Override
    public ExecuteContext before(ExecuteContext context) {
        LbContext.INSTANCE.setCurLoadbalancerType(LbContext.LOADBALANCER_SPRING);
        return context;
    }

    @Override
    public ExecuteContext after(ExecuteContext context) {
        getLoadBalancer((String) context.getArguments()[0]).ifPresent(context::changeResult);
        return context;
    }

    private Optional<Object> getLoadBalancer(String serviceId) {
        if (config == null || !RuleManager.INSTANCE.isConfigured()) {
            // 没有配置的情况下return null, 不影响原方法
            return Optional.empty();
        }
        final Optional<Object> cacheBalancer = SpringLoadbalancerCache.INSTANCE.getTargetServiceLbType(serviceId,
                this::createLoadbalancer);
        if (cacheBalancer.isPresent()) {
            return cacheBalancer;
        }
        return tryUseDefaultRule(serviceId);
    }

    private Optional<Object> tryUseDefaultRule(String serviceId) {
        final String defaultRule = config.getDefaultRule();
        if (defaultRule == null) {
            SpringLoadbalancerCache.INSTANCE.getNewCache().put(serviceId, Optional.empty());
            return Optional.empty();
        }
        final Optional<SpringLoadbalancerType> springLoadbalancerType = SpringLoadbalancerType
                .matchLoadbalancer(defaultRule);
        if (!springLoadbalancerType.isPresent()) {
            return Optional.empty();
        }
        return SpringLoadbalancerCache.INSTANCE.getNewCache().computeIfAbsent(serviceId,
            value -> createLoadbalancer(springLoadbalancerType.get(), serviceId));
    }

    private Optional<Object> createLoadbalancer(SpringLoadbalancerType type, String serviceId) {
        Class<?> clazz = getLoadBalancerClass(type);
        final Object provider = SpringLoadbalancerCache.INSTANCE.getProvider(serviceId);

        // 此处若junit OS为Linux将无法使用instanceof判断真正的provider类型, 此处当前仅使用判null处理
        if (provider == null) {
            return Optional.empty();
        }
        ObjectProvider<ServiceInstanceListSupplier> realProvider =
                (ObjectProvider<ServiceInstanceListSupplier>) provider;
        if (clazz == RoundRobinLoadBalancer.class) {
            return Optional.of(new RoundRobinLoadBalancer(realProvider, serviceId));
        } else {
            return Optional.of(new RandomLoadBalancer(realProvider, serviceId));
        }
    }

    private Class<?> getLoadBalancerClass(SpringLoadbalancerType type) {
        try {
            return type == SpringLoadbalancerType.RANDOM ? RandomLoadBalancer.class : RoundRobinLoadBalancer.class;
        } catch (NoClassDefFoundError e) {
            // 低版本没有RandomLoadBalancer，返回RoundRobinLoadBalancer
            return RoundRobinLoadBalancer.class;
        }
    }
}
