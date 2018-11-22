package com.umeng.libs.share;

import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

public class ShareType {
    public static final String GOOGLEPLUS = "GOOGLEPLUS";
    public static final String GENERIC = "GENERIC";
    public static final String SMS = "SMS";
    public static final String EMAIL = "EMAIL";
    public static final String SINA = "SINA";
    public static final String QZONE = "QZONE";
    public static final String QQ = "QQ";
    public static final String RENREN = "RENREN";
    public static final String WEIXIN = "WEIXIN";
    public static final String WEIXIN_CIRCLE = "WEIXIN_CIRCLE";
    public static final String WEIXIN_FAVORITE = "WEIXIN_FAVORITE";
    public static final String TENCENT = "TENCENT";
    public static final String DOUBAN = "DOUBAN";
    public static final String FACEBOOK = "FACEBOOK";
    public static final String FACEBOOK_MESSAGER = "FACEBOOK_MESSAGER";
    public static final String TWITTER = "TWITTER";
    public static final String LAIWANG = "LAIWANG";
    public static final String LAIWANG_DYNAMIC = "LAIWANG_DYNAMIC";
    public static final String YIXIN = "YIXIN";
    public static final String YIXIN_CIRCLE = "YIXIN_CIRCLE";
    public static final String INSTAGRAM = "INSTAGRAM";
    public static final String PINTEREST = "PINTEREST";
    public static final String EVERNOTE = "EVERNOTE";
    public static final String POCKET = "POCKET";
    public static final String LINKEDIN = "LINKEDIN";
    public static final String FOURSQUARE = "FOURSQUARE";
    public static final String YNOTE = "YNOTE";
    public static final String WHATSAPP = "WHATSAPP";
    public static final String LINE = "LINE";
    public static final String FLICKR = "FLICKR";
    public static final String TUMBLR = "TUMBLR";
    public static final String ALIPAY = "ALIPAY";
    public static final String KAKAO = "KAKAO";
    public static final String DROPBOX = "DROPBOX";
    public static final String VKONTAKTE = "VKONTAKTE";
    public static final String DINGTALK = "DINGTALK";
    public static final String MORE = "MORE";

    private static Map<String, SHARE_MEDIA> shareMediaMap = new HashMap<>();

    static {
        shareMediaMap.put(GOOGLEPLUS, SHARE_MEDIA.GOOGLEPLUS);
        shareMediaMap.put(GENERIC, SHARE_MEDIA.GENERIC);
        shareMediaMap.put(SMS, SHARE_MEDIA.SMS);
        shareMediaMap.put(EMAIL, SHARE_MEDIA.EMAIL);
        shareMediaMap.put(SINA, SHARE_MEDIA.SINA);
        shareMediaMap.put(QZONE, SHARE_MEDIA.QZONE);
        shareMediaMap.put(QQ, SHARE_MEDIA.QQ);
        shareMediaMap.put(RENREN, SHARE_MEDIA.RENREN);
        shareMediaMap.put(WEIXIN, SHARE_MEDIA.WEIXIN);
        shareMediaMap.put(WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_CIRCLE);
        shareMediaMap.put(WEIXIN_FAVORITE, SHARE_MEDIA.WEIXIN_FAVORITE);
        shareMediaMap.put(TENCENT, SHARE_MEDIA.TENCENT);
        shareMediaMap.put(DOUBAN, SHARE_MEDIA.DOUBAN);
        shareMediaMap.put(FACEBOOK, SHARE_MEDIA.FACEBOOK);
        shareMediaMap.put(FACEBOOK_MESSAGER, SHARE_MEDIA.FACEBOOK_MESSAGER);
        shareMediaMap.put(TWITTER, SHARE_MEDIA.TWITTER);
        shareMediaMap.put(LAIWANG, SHARE_MEDIA.LAIWANG);
        shareMediaMap.put(LAIWANG_DYNAMIC, SHARE_MEDIA.LAIWANG_DYNAMIC);
        shareMediaMap.put(YIXIN, SHARE_MEDIA.YIXIN);
        shareMediaMap.put(YIXIN_CIRCLE, SHARE_MEDIA.YIXIN_CIRCLE);
        shareMediaMap.put(INSTAGRAM, SHARE_MEDIA.INSTAGRAM);
        shareMediaMap.put(PINTEREST, SHARE_MEDIA.PINTEREST);
        shareMediaMap.put(EVERNOTE, SHARE_MEDIA.EVERNOTE);
        shareMediaMap.put(POCKET, SHARE_MEDIA.POCKET);
        shareMediaMap.put(LINKEDIN, SHARE_MEDIA.LINKEDIN);
        shareMediaMap.put(FOURSQUARE, SHARE_MEDIA.FOURSQUARE);
        shareMediaMap.put(YNOTE, SHARE_MEDIA.YNOTE);
        shareMediaMap.put(WHATSAPP, SHARE_MEDIA.WHATSAPP);
        shareMediaMap.put(LINE, SHARE_MEDIA.LINE);
        shareMediaMap.put(FLICKR, SHARE_MEDIA.FLICKR);
        shareMediaMap.put(TUMBLR, SHARE_MEDIA.TUMBLR);
        shareMediaMap.put(ALIPAY, SHARE_MEDIA.ALIPAY);
        shareMediaMap.put(KAKAO, SHARE_MEDIA.KAKAO);
        shareMediaMap.put(DROPBOX, SHARE_MEDIA.DROPBOX);
        shareMediaMap.put(VKONTAKTE, SHARE_MEDIA.VKONTAKTE);
        shareMediaMap.put(DINGTALK, SHARE_MEDIA.DINGTALK);
        shareMediaMap.put(MORE, SHARE_MEDIA.MORE);

    }

    public static SHARE_MEDIA getShareMedia(String shareType) {
        return shareMediaMap.get(shareType);
    }
}
