package com.umeng.libs.share;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.libs.share.config.ShareConfig;
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

/**
 * 通过友盟集成QQ、朋友圈、微信分享
 */
public class ShareTool {
    protected Activity mActivity;
    private boolean mIsWithIconBG;
    private Callback mCallback;
    protected String mSharePanelTitle;
    protected ShareInfo mShareInfo = new ShareInfo();

    /**
     * 使用setShareboardclickCallback() 可以进行分享面板不同按钮的点击回调
     */
    public void openShareWin() {
        if (!isShareInfoValid()) {
            return;
        }
        ShareAction shareAction = new ShareAction(mActivity);
        ShareBoardlistener shareBoardlistener = new ShareBoardListenerImpl(shareAction);
        //TODO: 使用addButton函数代替setDisplayList，这样就可以完整通过ShareType和config目录下进行扩展
        shareAction.setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ)
                .setShareboardclickCallback(shareBoardlistener);
        showShareWindow(mIsWithIconBG, shareAction);
    }

    /**
     * 直接调用对应平台分享，适用于定制分享面板和无分享面板场景
     */
    public void shareTo(String shareType) {
        if (!isShareInfoValid()) {
            return;
        }
        UMWeb umWeb = convert(mShareInfo, mActivity);
        new ShareAction(mActivity)
                .setPlatform(ShareTypeMapping.getShareMedia(shareType))
                .withMedia(umWeb)
                .setCallback(new CustomShareListener(mActivity))
                .share();
        if (mCallback != null) {
            mCallback.onShared(shareType);
        }
    }

    private boolean isShareInfoValid() {
        boolean result = true;
        if (TextUtils.isEmpty(mShareInfo.url)) {
            result = false;
            Toast.makeText(mActivity, mActivity.getString(R.string.empty_url_notice), Toast.LENGTH_LONG).show();
        }
        return result;
    }

    private class ShareBoardListenerImpl implements ShareBoardlistener {
        private final ShareAction shareAction;

        private ShareBoardListenerImpl(final ShareAction shareAction) {
            this.shareAction = shareAction;
        }

        /**
         * 在下面的条件判断中，可以针对不同分享渠道进行url、标题、描述、图片的定制
         */
        @Override
        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA shareMedia) {
            UMWeb umWeb = convert(mShareInfo, mActivity);

            ShareInfo shareInfo = getShareInfo(ShareTypeMapping.getShareType(shareMedia));
            if (shareInfo != null) {
                umWeb = convert(shareInfo, mActivity);
            }
            shareAction.withMedia(umWeb)
                    .setPlatform(shareMedia)
                    .setCallback(new CustomShareListener(mActivity))
                    .share();
        }
    }

    protected ShareInfo getShareInfo(String shareType) {
        return mShareInfo;
    }

    protected UMWeb convert(ShareInfo shareInfo, Activity activity) {
        UMWeb umWeb;
        umWeb = new UMWeb(shareInfo.url);
        if (!TextUtils.isEmpty(shareInfo.title)) {
            umWeb.setTitle(shareInfo.title);
        }
        if (!TextUtils.isEmpty(shareInfo.description)) {
            umWeb.setTitle(shareInfo.description);
        }
        if (!TextUtils.isEmpty(shareInfo.thumbUrl)) {
            umWeb.setThumb(new UMImage(activity, shareInfo.thumbUrl));
        }

        return umWeb;
    }

    public static void init(Context context, String umengAppKey) {
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(context, umengAppKey, "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "");
        ShareTypeMapping.initAllPlatforms();
    }

    public void releaseActivity(Activity activity) {
        UMShareAPI.get(activity).release();
    }

    private void showShareWindow(boolean mIsWithBG, ShareAction shareAction) {
        ShareBoardConfig config = new ShareBoardConfig();
        if (!TextUtils.isEmpty(mSharePanelTitle)) {
            config.setTitleText(mSharePanelTitle);
        }
        if (mIsWithBG) {
            config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_ROUNDED_SQUARE);
        } else {
            config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        }
        shareAction.open(config);
    }

    public class ShareInfo {
        private String url;
        private String thumbUrl;
        private String title;
        private String description;

        public ShareTool url(String url) {
            this.url = url;
            return ShareTool.this;
        }

        public ShareTool thumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
            return ShareTool.this;
        }

        public ShareTool title(String title) {
            this.title = title;
            return ShareTool.this;
        }

        public ShareTool description(String description) {
            this.description = description;
            return ShareTool.this;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbUrl() {
            return thumbUrl;
        }

        public void setThumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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
            goToMarket(mActivity.get(), ShareTypeMapping.getApkPackageName(platform));
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

    public ShareInfo shareInfo() {
        return mShareInfo;
    }

    public ShareTool activity(@NonNull Activity activity) {
        this.mActivity = activity;
        return this;
    }

    /**
     * 是否显示分享图标背景
     */
    public ShareTool isWithIconBG(boolean isWithIconBG) {
        this.mIsWithIconBG = isWithIconBG;
        return this;
    }

    /**
     * 设置完成分享后的回调
     * 目前仅调用shareTo的场景会使用到该callback。
     *
     * @return
     */
    public ShareTool callback(@NonNull Callback callback) {
        this.mCallback = callback;
        return this;
    }

    /**
     * 设置分享面板的标题
     */
    public ShareTool sharePanelTitle(String sharePanelTitle) {
        this.mSharePanelTitle = sharePanelTitle;
        return this;
    }

    public static void addPlatformConfig(String shareType, ShareConfig shareConfig) {
        ShareTypeMapping.addConfig(shareType, shareConfig);
    }

    public interface Callback {
        /**
         * 分享按钮被点击时调用，如点击了分享面板上QQ、微信的图标
         *
         * @param shareType
         */
        void onShared(String shareType);
    }
}
