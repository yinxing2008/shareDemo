package com.example.test.sharedemo;

import android.app.Application;

import com.umeng.libs.ShareTool;


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
        ShareTool shareTool = ShareTool.getInstance();
        shareTool.setUmengAppKey("59892f08310c9307b60023d0");
        shareTool.setWeixinAppId("wxdc1e388c3822c80b");
        shareTool.setWeixinAppSignature("3baf1193c85774b3fd9d18447d76cab0");
        shareTool.setQqAppId("100424468");
        shareTool.setQqAppKey("c7394704798a158208a74ab60104f0ba");
        shareTool.init(this);
    }

}
