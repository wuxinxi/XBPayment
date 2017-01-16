package com.payment.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.payment.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：Tangren_ on 2017/1/12 15:47.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public abstract class BaseNormalActivity extends AppCompatActivity {

    public abstract void initView();

    public abstract int initLayout();

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        unbinder = ButterKnife.bind(this);
        initView();
    }

    /**
     * 进入下一个Activity
     *
     * @param intent
     */
    protected void startActivityFromActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    /**
     * finish当前的Activity
     */
    protected void finishCurrentActivity() {
        finish();
        overridePendingTransition(0, R.anim.base_slide_right_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.base_slide_right_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(0, R.anim.base_slide_right_out);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
