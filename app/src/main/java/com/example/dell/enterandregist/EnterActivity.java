package com.example.dell.enterandregist;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class EnterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editPerson,editCode;  //用户名，密码输入框
    private TextView textViewR;    //注册按钮
    private Button btn;   //登录按钮
    private Button btnforget;   //忘记密码按钮
    private String currentUsername,currentPassword;  //用于加载输入完成后的用户名和密码
    private ImageView qq,weixin,weibo;//qq，微信，微博三方登录按钮
    private boolean autoLogin = false;
    private boolean progressShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.login_enter );
        init();   //初始化方法
    }

    private void init() {
        btn = (Button) findViewById(R.id.bn_common_login);
        btn.setOnClickListener(this);
        btnforget = (Button) findViewById(R.id.bn_forget_password);
        btnforget.setOnClickListener ( this );
        editCode = (EditText) findViewById(R.id.et_password);
        editPerson = (EditText) findViewById(R.id.et_username);
        textViewR = (TextView) findViewById(R.id.tv_register);
        qq = (ImageView) findViewById(R.id.iv_qq_login);
        weixin = (ImageView) findViewById(R.id.iv_weixin_login);
        weibo = (ImageView) findViewById(R.id.iv_sina_login);
        qq.setOnClickListener(this);
        weixin.setOnClickListener(this);
        weibo.setOnClickListener(this);
        textViewR.setOnClickListener(this);
    }

    /**
     * 点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bn_common_login:  //登录按钮
                login(v);
                break;
            case R.id.tv_register:  //注册按钮
                Intent intent = new Intent(this, RegisterActivity.class);//跳转注册界面
                startActivity(intent);
                finish();//销毁当前界面
                break;
            case R.id.bn_forget_password:  //忘记密码按钮
                Intent intent1 = new Intent(this, ForgetActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.iv_qq_login:  //QQ登录
                Toast.makeText(this, "QQ登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_weixin_login:  //微信登录
                Toast.makeText(this, "微信登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_sina_login:    //微博登录
                Toast.makeText(this, "微博登录", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (autoLogin) {
            return;
        }
    }

    /**
     * 登录
     *
     * @param view
     */
    public void login(View view) {

        currentUsername = editPerson.getText().toString().trim(); //去除空格，获取手机号
        currentPassword = editCode.getText().toString().trim();  //去除空格，获取密码

        if (TextUtils.isEmpty(currentUsername)) { //判断手机号是不是为空
            Toast.makeText(this, "手机号不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(currentPassword)) {  //判断密码是不是空
            Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressShow = true;
        final ProgressDialog pd = new ProgressDialog(EnterActivity.this);  //初始化等待动画
        /**
         * 设置监听
         * */
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                progressShow = false;   //设置Boolean值为false
            }
        });
        pd.setMessage("正在登录....");  //等待动画的标题
        pd.show();  //显示等待动画

        /*
        * 模拟后台请求
        * */
        new Thread(new Runnable() {
            public void run() {
                //在此处睡眠两秒
                try {
                    Thread.sleep(2000);  //在此处睡眠两秒
                } catch (InterruptedException e) {
                }

                /**
                 * 两秒之后
                 * */
                pd.dismiss();    //等待条消失
                Intent intent = new Intent(EnterActivity.this, MainActivity.class);  //进入主界面
                startActivity(intent);  //开始跳转
                finish();  //finish掉此界面
            }
        }).start();  //开始线程


    }
}
