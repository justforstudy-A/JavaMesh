/*
 * Copyright (C) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package com.huawei.apm.bootstrap.serialize;

import java.util.Locale;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lubanops.apm.bootstrap.log.LogFactory;

/**
 * 序列化管理者，提供对ClassLoader键消息传递的类型转换方法
 *
 * @author h30007557
 * @version 1.0.0
 * @since 2021/9/3
 */
public class SerializerHolder {
    /**
     * 日志
     */
    private static final Logger LOGGER = LogFactory.getLogger();

    /**
     * 序列化器，用于对参数和返回值进行序列化和反序列化
     */
    private static Serializer serializer;

    /**
     * 初始化标记，保证{@link #initialize(ClassLoader)}方法只被调用一次
     */
    private static boolean initFlag = false;

    /**
     * 初始化
     *
     * @param spiLoader 宿主的classLoader，用作spi查找
     */
    public static synchronized void initialize(ClassLoader spiLoader) {
        if (initFlag) {
            return;
        }
        serializer = getSerializer(spiLoader);
        initFlag = true;
    }

    private static Serializer getSerializer(ClassLoader spiLoader) {
        for (Serializer impl : ServiceLoader.load(Serializer.class, spiLoader)) {
            return impl;
        }
        LOGGER.log(Level.WARNING, String.format(Locale.ROOT, "Missing implement of [%s], use [%s].",
                Serializer.class.getName(), Serializer.DefaultSerializer.class.getName()));
        return new Serializer.DefaultSerializer();
    }

    /**
     * 序列化对象
     *
     * @param t   被序列化的对象
     * @param <T> 被序列化的对象类型
     * @return 序列化之后的字节码
     */
    public static <T> byte[] serialize(T t) {
        if (!initFlag) {
            LOGGER.log(Level.WARNING, String.format(Locale.ROOT, "[%s] hasn't been initialized, please check.",
                    SerializerHolder.class.getName()));
            return new byte[0];
        }
        return serializer.serialize(t);
    }

    /**
     * 反序列化成某对象
     *
     * @param bytes 字节码
     * @param clazz 反序列化的目标对象Class
     * @param <T>   反序列化的目标对象类型
     * @return 反序列化的目标对象
     */
    public static <T> T deserialize(byte[] bytes, Class<T> clazz) {
        if (!initFlag) {
            LOGGER.log(Level.WARNING, String.format(Locale.ROOT, "[%s] hasn't been initialized, please check.",
                    SerializerHolder.class.getName()));
            return null;
        }
        return serializer.deserialize(bytes, clazz);
    }
}
