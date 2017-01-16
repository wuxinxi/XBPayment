package com.payment.modul.bill.view;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.payment.R;
import com.payment.base.BaseActivity;
import com.payment.entity.Bill;
import com.payment.modul.bill.presenter.BillPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：Tangren_ on 2017/1/14 17:21.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class BillActivity extends BaseActivity<IBillView, BillPresenter> implements IBillView {
    @BindView(R.id.mtoolbar)
    Toolbar mtoolbar;
    @BindView(R.id.recyclerView)
    SuperRecyclerView recyclerView;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;

    private String indexTime = "2016-01-01";
    private String currentDate;
    private List<Bill.VarListBean> list = new ArrayList<Bill.VarListBean>();
    private static final int RESULT_CODE = 1;

    @Override
    protected BillPresenter createPresenter() {
        return new BillPresenter(this);
    }

    @Override
    public void initView() {
        mtoolbar.setTitle("账单");
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_bill;
    }


    @Override
    public void onSuccess(List<Bill.VarListBean> varListBeen) {

    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
