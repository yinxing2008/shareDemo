package com.example.test.sharedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.umeng.libs.share.ShareTool;
import com.umeng.libs.share.ShareType;
import com.umeng.soexample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String url = "http://www.qq.com/";
        final String thumbUrl = "https://www.baidu.com/img/bd_logo1.png";
        final String title = "测试标题";
        final String description = "测试描述";
        findViewById(R.id.btn_common).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ShareTool().activity(MainActivity.this)
                        .isWithIconBG(true)
                        .shareInfo().url(url)
                        .shareInfo().thumbUrl(thumbUrl)
                        .shareInfo().title(title)
                        .shareInfo().description(description)
                        .openShareWin();
            }
        });
        findViewById(R.id.btn_diff_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CustShareTool().activity(MainActivity.this)
                        .isWithIconBG(true)
                        .shareInfo().url(url)
                        .shareInfo().thumbUrl(thumbUrl)
                        .shareInfo().title(title)
                        .shareInfo().description(description)
                        .openShareWin();
            }
        });
        findViewById(R.id.btn_cust_panel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ShareToolWithCustPanel().activity(MainActivity.this)
                        .sharePanelTitle("分享")
                        .shareInfo().url(url)
                        .shareInfo().thumbUrl(thumbUrl)
                        .shareInfo().title(title)
                        .shareInfo().description(description)
                        .callback(new ShareTool.Callback() {
                            @Override
                            public void onShared(String shareType) {
                                Toast.makeText(MainActivity.this, "分享到了:" + shareType, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .openShareWin();
            }
        });

        findViewById(R.id.shareToWeixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ShareTool().activity(MainActivity.this)
                        .shareInfo().url(url)
                        .shareInfo().thumbUrl(thumbUrl)
                        .shareInfo().title(title)
                        .shareInfo().description(description)
                        .shareTo(ShareType.WEIXIN);
            }
        });

    }


}
