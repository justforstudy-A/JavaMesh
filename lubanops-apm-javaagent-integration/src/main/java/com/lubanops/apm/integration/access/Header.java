package com.lubanops.apm.integration.access;

import com.lubanops.apm.integration.Constants;
import com.lubanops.apm.integration.utils.JSON;

/**
 * @author
 * @since 2020/5/7
 **/
public abstract class Header {
    /**
     * 是否需要给回复信息
     */
    private boolean needResponse = false;

    public boolean isNeedResponse() {
        return needResponse;
    }

    public void setNeedResponse(boolean needResponse) {
        this.needResponse = needResponse;
    }

    /**
     * 转成json的二进制
     * @return
     */
    public byte[] toBytes() {

        String s = JSON.toJSONString(this);

        return s.getBytes(Constants.DEFAULT_CHARSET);

    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
