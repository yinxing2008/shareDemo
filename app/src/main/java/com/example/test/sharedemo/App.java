package com.example.test.sharedemo;

import android.app.Application;

import com.umeng.libs.share.ShareTool;
import com.umeng.libs.share.ShareType;
import com.umeng.libs.share.config.QQConfig;
import com.umeng.libs.share.config.WeiXinConfig;


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
        ShareTool.addPlatformConfig(ShareType.QQ, new QQConfig("100424468","c7394704798a158208a74ab60104f0ba"));
        ShareTool.addPlatformConfig(ShareType.WEIXIN, new WeiXinConfig("wxdc1e388c3822c80b","3baf1193c85774b3fd9d18447d76cab0"));
        ShareTool.init(getApplicationContext(),"59892f08310c9307b60023d0");
    }

}
