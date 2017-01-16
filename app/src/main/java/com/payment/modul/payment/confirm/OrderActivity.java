package com.payment.modul.payment.confirm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.payment.R;
import com.payment.base.BaseNormalActivity;
import com.payment.entity.PayInfo;
import com.payment.modul.payment.swept.view.SweptActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Tangren_ on 2017/1/13 18:42.
 * 邮箱：wu_tangren@163.com
 * TODO:确认下单
 */

public class OrderActivity extends BaseNormalActivity implements AppBarLayout.OnOffsetChangedListener {
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.countAmout)
    TextView countAmout;
    @BindView(R.id.mToolbar)
    Toolbar mToolbar;
    @BindView(R.id.coll)
    CollapsingToolbarLayout coll;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.cashier)
    TextView cashier;
    @BindView(R.id.shishou)
    TextView shishou;
    @BindView(R.id.confirmOrder)
    Button confirmOrder;
    @BindView(R.id.payType)
    TextView payType;

    private static String KEY = "payInfo";

    private PayInfo info;

    public static void start(Context context, PayInfo info) {
        Intent intent = new Intent(context, OrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY, info);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_confirm_order;
    }

    @Override
    public void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        coll.setTitleEnabled(false);
        appBar.addOnOffsetChangedListener(this);

        info = getIntent().getParcelableExtra(KEY);
        payType.setText(info.getType());
        cashier.setText(info.getAmount());
        shishou.setText(info.getAmount());
        countAmout.setText(info.getAmount());
    }

    @OnClick(R.id.confirmOrder)
    public void onClick() {
        SweptActivity.start(OrderActivity.this, info);
        finish();
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0)
            mToolbar.setTitle("");
        else
            mToolbar.setTitle("下单");
    }
}
