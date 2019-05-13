package com.example.dell.enterandregist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextP, editSMS, editTextCT;  //是手机号，验证码，密码输入框
    private Button button,SMSBtn;   //注册按钮、获取验证码按钮
    private TextView enterText;     //登录按钮
    private ImageView returnImage;   //返回按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_regist);
        init();  //初始化控件
    }

    /*
    * 初始化控件的方法
    * */
    private void init() {
        editTextP = (EditText) findViewById(R.id.et_phone_num);
        editSMS = (EditText) findViewById(R.id.et_sms_code);
        editTextCT = (EditText) findViewById(R.id.et_password);
        button = (Button) findViewById(R.id.bn_immediateRegistration);
        button.setOnClickListener(this);
        enterText = (TextView) findViewById(R.id.tv_enter);
        enterText.setOnClickListener(this);
        returnImage = (ImageView) findViewById(R.id.iv_return);
        returnImage.setOnClickListener(this);
        SMSBtn = (Button) findViewById(R.id.bn_sms_code);
        SMSBtn.setOnClickListener(this);
    }

    /*
    * 点击事件
    * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bn_immediateRegistration://注册按钮
                register();//注册
                break;
            case R.id.tv_enter: //登录
                returnEnter();  //回到登录界面
                break;
            case R.id.iv_return:  //返回
                returnEnter();  //回到登录界面
                break;
            case R.id.bn_sms_code:  //获取验证码
                final String username = editTextP.getText().toString().trim();  //获取电话号
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(this, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
                    editTextP.requestFocus();
                }else {
                    Toast.makeText(this, "验证码获取成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /*
    * 跳转到登录界面
    * */
    private void returnEnter() {
        Intent intent = new Intent(this,EnterActivity.class);
        startActivity(intent);
        finish();
    }

    /*
    * 注册方法
    * */
    public void register() {
        final String username = editTextP.getText().toString().trim();  //获取电话号
        final String password = editSMS.getText().toString().trim();    //获取验证码
        String confirm_password = editTextCT.getText().toString().trim();  //获取设置密码
        if (TextUtils.isEmpty(username)) {  //当手机号没有输入时
            Toast.makeText(this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
            editTextP.requestFocus();//使输入框失去焦点，隐藏键盘
            return;
        } else if (TextUtils.isEmpty(password)) {//当验证码没有输入时
            Toast.makeText(this, "验证码不能为空！", Toast.LENGTH_SHORT).show();
            editSMS.requestFocus();//使输入框失去焦点
            return;
        } else if (TextUtils.isEmpty(confirm_password)) {//当注册密码没有输入时
            Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
            editTextCT.requestFocus();//使输入框失去焦点
            return;
        }
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            final ProgressDialog pd = new ProgressDialog(this);  //初始化等待条
            pd.setMessage("正在注册……"); //  等待条信息
            pd.show();    //显示等待条

            /*
            * 模拟后台请求
            * */
            new Thread(new Runnable() {
                public void run() {
                    //注册的操作放在此处
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {

                    }
                    pd.dismiss();  //等待条消失
                    returnEnter();//跳转到登录界面
                   // Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                }
            }).start(); //开启线程

        }
    }
}
