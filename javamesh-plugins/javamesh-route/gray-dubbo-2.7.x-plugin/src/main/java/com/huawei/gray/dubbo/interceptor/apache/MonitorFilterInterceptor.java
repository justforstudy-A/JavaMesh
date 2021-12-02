/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2021. All rights reserved.
 */

package com.huawei.gray.dubbo.interceptor.apache;

import com.huawei.javamesh.core.agent.common.BeforeResult;
import com.huawei.javamesh.core.agent.interceptor.InstanceMethodInterceptor;
import com.huawei.javamesh.core.lubanops.bootstrap.log.LogFactory;
import com.huawei.javamesh.core.service.ServiceManager;
import com.huawei.gray.dubbo.service.MonitorFilterService;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 增强MonitorFilter类的invoke方法，更改路由信息
 *
 * @author pengyuyi
 * @since 2021年6月28日
 */
public class MonitorFilterInterceptor implements InstanceMethodInterceptor {
    private static final Logger LOGGER = LogFactory.getLogger();

    private MonitorFilterService monitorFilterService;

    @Override
    public void before(Object obj, Method method, Object[] arguments, BeforeResult beforeResult) throws Exception {
        monitorFilterService = ServiceManager.getService(MonitorFilterService.class);
    }

    /**
     * 每次进行Dubbo请求时，更换请求的IP地址
     *
     * @param obj 增强的类
     * @param method 增强的方法
     * @param arguments 增强方法的所有参数
     * @param result the method's original return value. May be null if the method triggers an exception.
     * @return 返回值
     * @throws Exception 增强时可能出现的异常
     */
    @Override
    public Object after(Object obj, Method method, Object[] arguments, Object result) throws Exception {
        monitorFilterService.after(obj, method, arguments, result);
        return result;
    }

    @Override
    public void onThrow(Object obj, Method method, Object[] arguments, Throwable throwable) {
        monitorFilterService.onThrow(obj, method, arguments, throwable);
        LOGGER.log(Level.SEVERE, "MonitorFilter is error!", throwable);
    }
}