package com.example.test.sharedemo;

import com.umeng.libs.share.ShareTool;
import com.umeng.libs.share.ShareType;

/**
 * 对不同的渠道定制不同的分享信息，支持url、标题、描述、图片的定制
 */
public class CustShareTool extends ShareTool {

    @Override
    protected ShareInfo getShareInfo(String shareType) {
        if (ShareType.QQ.equals(shareType)) {
            mShareInfo.title("QQ_" + mShareInfo.getTitle());
            return mShareInfo;
        } else if (ShareType.WEIXIN.equals(shareType)) {
            mShareInfo.title("微信_" + mShareInfo.getTitle());
            return mShareInfo;
        } else if (ShareType.WEIXIN_CIRCLE.equals(shareType)) {
            mShareInfo.title("朋友圈_" + mShareInfo.getTitle());
            return mShareInfo;
        }
        return mShareInfo;
    }

}
