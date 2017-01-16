package com.payment.modul.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.payment.R;
import com.payment.dialog.Loading;
import com.payment.modul.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：Tangren_ on 2017/1/12 15:45.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.user)
    EditText user;
    @BindView(R.id.psw)
    EditText psw;
    @BindView(R.id.login)
    Button login;

    private Loading loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0以上
            View view = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            view.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4以上
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.flags = WindowManager.LayoutParams.FLAG_SPLIT_TOUCH | params.flags;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
    public void onClick() {
        loading = new Loading(this);
        loading.show();
        if (user.getText().toString().trim().equals("Test") && psw.getText().toString().trim().equals("1234")) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
            finish();
            loading.stop();
        } else {
            loading.stop();
            loading.dismiss();
            Snackbar.make(user, "账号或密码错误！", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
            loading = null;
        }
    }
}
