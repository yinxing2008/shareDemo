package com.example.test.sharedemo;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.umeng.soexample.R;

public class CustomShareDialog extends Dialog {
    public CustomShareDialog(@NonNull Context context) {
        super(context,R.style.common_dialog);
        setContentView(R.layout.customshare_layout);
        changeDialogStyle();
    }

    /**
     * 设置dialog居下占满屏幕
     */
    private void changeDialogStyle() {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attr = window.getAttributes();
            if (attr != null) {
                attr.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                attr.width = ViewGroup.LayoutParams.MATCH_PARENT;
                attr.gravity = Gravity.BOTTOM;
                window.setAttributes(attr);
            }
        }
    }
}
