package com.umeng.libs.share;

import com.umeng.libs.share.config.ShareConfig;

public class ShareBean {
    public String shareType;
    public String apkPackageName;
    public ShareConfig shareConfig;

    public ShareBean() {
    }

    public ShareBean(String shareType, String apkPackageName) {
        this.shareType = shareType;
        this.apkPackageName = apkPackageName;
    }

}
