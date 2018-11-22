package com.umeng.libs.share.config;

import com.umeng.socialize.PlatformConfig;

public class QQConfig implements ShareConfig {

    private String appId;
    private String appKey;

    public QQConfig(String appId, String appKey) {
        this.appId = appId;
        this.appKey = appKey;
    }

    @Override
    public void init() {
        PlatformConfig.setQQZone(appId, appKey);
    }
}
