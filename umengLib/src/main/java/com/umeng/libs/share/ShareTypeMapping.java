package com.umeng.libs.share;

import com.umeng.libs.share.config.ShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

public class ShareTypeMapping extends ShareType {
    private static Map<String, ShareBean> map = new HashMap<>();

    static {
        map.put(QQ, new ShareBean(QQ, "com.tencent.mobileqq"));
        map.put(WEIXIN, new ShareBean(WEIXIN, "com.tencent.mm"));
        map.put(WEIXIN_CIRCLE, new ShareBean(WEIXIN_CIRCLE, "com.tencent.mm"));
    }

    public static void addMapping(String shareType, String apkPackageName) {
        map.put(shareType, new ShareBean(shareType, apkPackageName));
    }

    public static void addConfig(String shareType, ShareConfig shareConfig) {
        map.get(shareType).shareConfig = shareConfig;
    }

    public static void initAllPlatforms() {
        for (Map.Entry<String, ShareBean> entry : map.entrySet()) {
            ShareBean shareBean = entry.getValue();
            if (shareBean.shareConfig != null) {
                shareBean.shareConfig.init();
            }
        }
    }

    public static String getShareType(SHARE_MEDIA shareMedia) {
        String result = "";
        ShareBean shareBean = getShareBean(shareMedia);
        if (shareBean != null) {
            result = shareBean.shareType;
        }
        return result;
    }

    public static String getApkPackageName(SHARE_MEDIA shareMedia) {
        String result = "";
        ShareBean shareBean = getShareBean(shareMedia);
        if (shareBean != null) {
            result = shareBean.apkPackageName;
        }
        return result;
    }

    private static ShareBean getShareBean(SHARE_MEDIA shareMedia) {
        ShareBean result = null;
        for (Map.Entry<String, ShareBean> entry : map.entrySet()) {
            ShareBean shareBean = entry.getValue();
            SHARE_MEDIA shareMediaForCurrentEntry = ShareType.getShareMedia(shareBean.shareType);
            if (shareMediaForCurrentEntry.equals(shareMedia)) {
                result = shareBean;
                break;
            }
        }
        return result;
    }
}
