package com.payment.modul.payment.swept.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.payment.R;
import com.payment.base.BaseActivity;
import com.payment.entity.PayInfo;
import com.payment.modul.payment.mainsweep.view.MainSweepActivity;
import com.payment.modul.payment.swept.presenter.SweptPresenter;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Tangren_ on 2017/1/13 18:26.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class SweptActivity extends BaseActivity<OnSweptListener, SweptPresenter> implements OnSweptListener {


    @BindView(R.id.fl_my_container)
    FrameLayout flMyContainer;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.mtoolbar)
    Toolbar mtoolbar;
    @BindView(R.id.flashLamp)
    ImageView flashLamp;
    @BindView(R.id.jianyi)
    TextView jianyi;
    @BindView(R.id.checkOrder)
    Button checkOrder;
    @BindView(R.id.mainsweep)
    Button mainsweep;
    @BindView(R.id.activity_second)
    FrameLayout activitySecond;

    private static String KEY = "payInfo";

    private PayInfo info;

    private CaptureFragment captureFragment;

    public static void start(Context context, PayInfo info) {
        Intent intent = new Intent(context, SweptActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY, info);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    @Override
    public int initLayout() {
        return R.layout.activity_swept;
    }


    @Override
    protected SweptPresenter createPresenter() {
        return new SweptPresenter(this);
    }

    @Override
    public void initView() {
        info = getIntent().getParcelableExtra(KEY);
        mtoolbar.setTitle(info.getType());
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        captureFragment = new CaptureFragment();
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();

    }

    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            image.setVisibility(View.VISIBLE);
            image.setImageBitmap(mBitmap);
            mPresenter.fatch(result, info);
        }

        @Override
        public void onAnalyzeFailed() {
            Toast.makeText(SweptActivity.this, "扫码失败！", Toast.LENGTH_SHORT).show();
            finishCurrentActivity();
        }
    };


    @OnClick({R.id.checkOrder, R.id.mainsweep})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkOrder:
                mPresenter.checkOrder(info.getOrderNo());
                break;
            case R.id.mainsweep:
                MainSweepActivity.start(this, info);
                overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
                break;
        }
    }

    @Override
    public void onSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finishCurrentActivity();
    }

    @Override
    public void onFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckInfo(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
