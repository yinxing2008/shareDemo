package com.example.test.sharedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.umeng.libs.ShareTool;
import com.umeng.soexample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String url = "http://www.qq.com/";
        final String thumbUrl= "http://mat1.gtimg.com/www/qq2018/imgs/qq_logo_2018x2.png";
        final String title = "测试标题";
        final String description = "测试描述";
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareTool.getInstance().openShareWin(MainActivity.this,true,url,thumbUrl,title,description);
            }
        });
     }
}
