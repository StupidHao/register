package com.example.dell.enterandregist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView,tvshop,tvMore,tvCommunity,tvComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        textView = (TextView) findViewById ( R.id.tv_enterandregister );
        textView.setOnClickListener(this);
        tvComment = (TextView) findViewById ( R.id.tv_comment );
        tvComment.setOnClickListener(this);
        tvCommunity = (TextView) findViewById ( R.id.tv_community );
        tvCommunity.setOnClickListener(this);
        tvshop = (TextView) findViewById ( R.id.tv_shop );
        tvshop.setOnClickListener(this);
        tvMore = (TextView) findViewById ( R.id.tv_more );
        tvMore.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_enterandregister:  //登录/注册按钮
                Intent intent = new Intent(this,EnterActivity.class);//跳转登录界面
                startActivity(intent);
                finish();//销毁当前界面
                break;
            case R.id.tv_comment:
                Toast.makeText(this, "点评", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_community:
                Toast.makeText(this, "社区", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_shop:
                Toast.makeText(this, "店铺", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more:
                Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
