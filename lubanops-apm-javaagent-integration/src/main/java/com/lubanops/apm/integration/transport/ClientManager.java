/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2022. All rights reserved.
 */

package com.lubanops.apm.integration.transport;

import com.lubanops.apm.integration.transport.netty.client.NettyClientFactory;
import com.lubanops.apm.integration.transport.websocket.WebSocketFactory;

/**
 * 客户端连接管理类
 *
 * @author lilai
 * @version 0.0.1
 * @since 2021-08-07
 */
public class ClientManager {
    public static WebSocketFactory getWebSocketFactory() {
        return WebSocketFactory.getInstance();
    }

    public static NettyClientFactory getNettyClientFactory() {
        return NettyClientFactory.getInstance();
    }

}
