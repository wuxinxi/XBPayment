package com.payment.modul.bill.query;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.payment.R;
import com.payment.base.BaseNormalActivity;

import java.util.Calendar;

import butterknife.BindView;

/**
 * 作者：Tangren_ on 2017/1/16 09:47.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class QueryBill extends BaseNormalActivity implements AppBarLayout.OnOffsetChangedListener {
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.mToolbar)
    Toolbar mToolbar;
    @BindView(R.id.coll)
    CollapsingToolbarLayout coll;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.spinerTime)
    AppCompatSpinner spinerTime;
    @BindView(R.id.startTime)
    TextView startTime;
    @BindView(R.id.endTime)
    TextView endTime;
    @BindView(R.id.query)
    Button query;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private String timeRanges="今天";
    private boolean beaginTime = true;

    @Override
    public int initLayout() {
        return R.layout.activity_query_bill;
    }

    @Override
    public void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0)
            mToolbar.setTitle("");
        else mToolbar.setTitle("查询");
    }
}
