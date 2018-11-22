# shareDemo
**用途：**
1）通过友盟集成微信、qq分享样例
2）支持对不同分享方式定制分享内容，详见CustShareTool
3）支持完全定制分享面板，详见ShareToolWithCustPanel
**调用方法：**
1. 添加依赖包
- 项目根目录下build.gradle的allprojects->repositories下增加`maven { url 'https://jitpack.io' }`
- app工程目录下build.gradle的dependencies中增加`implementation 'com.github.cxyzy1:shareDemo:0.0.5'`
2. 注意需要Application中初始化，样例如下
```
/**
     * 初始化分享组件
     */
    private void initShareComponents() {
        ShareTool.addPlatformConfig(ShareType.QQ, new QQConfig("100424468","c7394704798a158208a74ab60104f0ba"));
        ShareTool.addPlatformConfig(ShareType.WEIXIN, new WeiXinConfig("wxdc1e388c3822c80b","3baf1193c85774b3fd9d18447d76cab0"));
        ShareTool.init(getApplicationContext(),"59892f08310c9307b60023d0");
    }
```
3. 普通调用方法
```
new ShareTool().activity(MainActivity.this)
                        .isWithBG(true)
                        .shareInfo().url(url)
                        .shareInfo().thumbUrl(thumbUrl)
                        .shareInfo().title(title)
                        .shareInfo().description(description)
                        .openShareWin();
```
4. 设置回调方式(用于获取分享给了哪个平台)
```
new ShareToolWithCustPanel().activity(MainActivity.this)
                        .sharePanelTitle("分享")
                        .shareInfo().url(url)
                        .shareInfo().thumbUrl(thumbUrl)
                        .shareInfo().title(title)
                        .shareInfo().description(description)
                        .callback(shareType -> Toast.makeText(MainActivity.this, "分享到了:" + shareType, Toast.LENGTH_SHORT).show())
                        .openShareWin();
```

 **效果图：**