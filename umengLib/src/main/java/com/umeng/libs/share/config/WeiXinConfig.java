package com.umeng.libs.share.config;

import com.umeng.socialize.PlatformConfig;

public class WeiXinConfig implements ShareConfig {
    private String appId;
    private String appSignature;

    public WeiXinConfig(String appId, String appSignature) {
        this.appId = appId;
        this.appSignature = appSignature;
    }

    @Override
    public void init() {
        PlatformConfig.setWeixin(appId, appSignature);
    }
}
