package com.umeng.libs;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ShareTool {
    private static volatile ShareTool mInstance;
    private Map<SHARE_MEDIA, String> appPackageNameMap = new HashMap<>();
    private String umengAppKey;
    private String weixinAppId;
    private String weixinAppSignature;
    private String qqAppId;
    private String qqAppKey;
    private ShareTool() {
        appPackageNameMap.put(SHARE_MEDIA.QQ, "com.tencent.mobileqq");
        appPackageNameMap.put(SHARE_MEDIA.WEIXIN, "com.tencent.mm");
        appPackageNameMap.put(SHARE_MEDIA.WEIXIN_CIRCLE, "com.tencent.mm");
    }
    public static ShareTool getInstance() {
        if (mInstance == null) {
            synchronized (ShareTool.class) {
                if (mInstance == null) {
                    mInstance = new ShareTool();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context)
    {
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(context, umengAppKey, "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "");
        PlatformConfig.setWeixin(weixinAppId,weixinAppSignature);
        PlatformConfig.setQQZone(qqAppId,qqAppKey);
    }

    public void openShareWin(final Activity activity, boolean isWithBG, final String url, final String thumbUrl, final String title, final String description) {
        final CustomShareListener mShareListener = new CustomShareListener(activity);
        ShareAction shareAction = new ShareAction(activity);

        shareAction = shareAction.setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ);

        shareAction = shareAction.setShareboardclickCallback(new ShareBoardlistener() {
            @Override
            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                UMWeb web = new UMWeb(url);
                web.setTitle(title);
                web.setDescription(description);
                web.setThumb(new UMImage(activity, thumbUrl));
                new ShareAction(activity).withMedia(web)
                        .setPlatform(share_media)
                        .setCallback(mShareListener)
                        .share();
            }
        });

        showShareWindow(isWithBG, shareAction);
//        return shareAction;
    }

    public void closeShareAction(ShareAction shareAction) {
        if (shareAction != null) {
            shareAction.close();
        }
    }

    public void releaseActivity(Activity activity) {
        UMShareAPI.get(activity).release();
    }

    private void showShareWindow(boolean isWithBG, ShareAction shareAction) {
        ShareBoardConfig config = new ShareBoardConfig();
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        if (isWithBG) {
            shareAction.open();
        } else {
            shareAction.open(config);
        }
    }

    private class CustomShareListener implements UMShareListener {
        private WeakReference<Activity> mActivity;

        private CustomShareListener(Activity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            goToMarket(mActivity.get(), appPackageNameMap.get(platform));
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
        }
    }

    private static void goToMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setUmengAppKey(String umengAppKey) {
        this.umengAppKey = umengAppKey;
    }

    public void setWeixinAppId(String weixinAppId) {
        this.weixinAppId = weixinAppId;
    }

    public void setWeixinAppSignature(String weixinAppSignature) {
        this.weixinAppSignature = weixinAppSignature;
    }

    public void setQqAppId(String qqAppId) {
        this.qqAppId = qqAppId;
    }

    public void setQqAppKey(String qqAppKey) {
        this.qqAppKey = qqAppKey;
    }
}
