# shareDemo
**用途：**
通过友盟集成微信、qq分享样例
支持对不同分享方式定制分享内容，定制样例见CustShareTool
**调用方法：**

```
new ShareTool().activity(MainActivity.this)
                        .isWithBG(true)
                        .shareInfo().url(url)
                        .shareInfo().thumbUrl(thumbUrl)
                        .shareInfo().title(title)
                        .shareInfo().description(description)
                        .openShareWin();
```

 **效果图：** 

![输入图片说明](https://gitee.com/cxyzy1/umengDemo/raw/master/shareDemo/screenshot/20180929172416.png "在这里输入图片标题")
![输入图片说明](https://gitee.com/cxyzy1/umengDemo/raw/master/shareDemo/screenshot/20180929172349.png "在这里输入图片标题")