package com.payment.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.payment.R;
import com.payment.dialog.Loading;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：Tangren_ on 2017/1/13 11:31.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    protected T mPresenter;

    protected abstract T createPresenter();

    private Unbinder unbinder;

    public abstract void initView();

    public abstract int initLayout();

    private Loading loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        unbinder = ButterKnife.bind(this);
        initView();
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    public void showLoading() {
        if (loading == null) {
            loading = new Loading(this);
            loading.setCanceledOnTouchOutside(false);
        }
        loading.show();
    }

    public void closeLoading() {
        if (loading != null && loading.isShowing()) {
            loading.stop();
            loading.dismiss();
        }
    }

    public BasePresenter getPresenter() {
        return null;
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
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mPresenter.detechView();
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

    protected void SecondFetch(String msg) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("交易结果")
                .setMessage(msg)
                .setPositiveButton("重试", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishCurrentActivity();
                    }
                }).show();
    }
}
