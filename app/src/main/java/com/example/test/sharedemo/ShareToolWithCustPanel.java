package com.example.test.sharedemo;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umeng.libs.share.ShareTool;
import com.umeng.libs.share.ShareType;
import com.umeng.soexample.R;

/**
 * 通过友盟集成QQ、朋友圈、微信分享
 */
public class ShareToolWithCustPanel extends ShareTool {
    private Dialog dialog;

    @Override
    public void openShareWin() {
        dialog = new CustomShareDialog(mActivity);
        showShareDialog();
    }

    private void showShareDialog() {
        ViewGroup weixinTouchLayout = dialog.findViewById(R.id.weixin_touch_layout);
        ViewGroup weixinCircleTouchLayout = dialog.findViewById(R.id.weixin_circle_touch_layout);
        ViewGroup qqTouchLayout = dialog.findViewById(R.id.qq_touch_layout);
        TextView btnCancel = dialog.findViewById(R.id.btn_cancel);
        TextView sharePanelTitle = dialog.findViewById(R.id.tv_share_panel_title);
        weixinTouchLayout.setOnClickListener(listener);
        weixinCircleTouchLayout.setOnClickListener(listener);
        qqTouchLayout.setOnClickListener(listener);
        btnCancel.setOnClickListener(listener);
        if (!TextUtils.isEmpty(mSharePanelTitle)) {
            sharePanelTitle.setText(mSharePanelTitle);
        }
        if (mActivity != null && !mActivity.isFinishing() && dialog != null) {
            dialog.show();
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.weixin_touch_layout) {
                shareTo(ShareType.WEIXIN);
            } else if (i == R.id.weixin_circle_touch_layout) {
                shareTo(ShareType.WEIXIN_CIRCLE);

            } else if (i == R.id.qq_touch_layout) {
                shareTo(ShareType.QQ);
            }
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }

    };
}
