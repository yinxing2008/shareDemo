package com.example.test.sharedemo;

import android.app.Application;

import com.umeng.libs.share.ShareTool;
import com.umeng.libs.share.ShareType;
import com.umeng.libs.share.config.QQConfig;
import com.umeng.libs.share.config.WeiXinConfig;
import com.umeng.soexample.R;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initShareComponents();
    }

    /**
     * 初始化分享组件
     */
    private void initShareComponents() {
        ShareTool.addPlatformConfig(ShareType.QQ, new QQConfig(getString(R.string.qq_app_id),getString(R.string.qq_app_key)));
        ShareTool.addPlatformConfig(ShareType.WEIXIN, new WeiXinConfig(getString(R.string.weixin_app_id),getString(R.string.weixin_app_signature)));
        ShareTool.init(getApplicationContext(),getString(R.string.umeng_key));
    }

}
