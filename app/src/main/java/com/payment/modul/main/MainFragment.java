package com.payment.modul.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.payment.R;
import com.payment.modul.payment.MainPayment;
import com.payment.ui.ImageTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：Tangren_ on 2017/1/12 17:57.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class MainFragment extends Fragment {

    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.payment)
    ImageTextView payment;
    @BindView(R.id.bill)
    ImageTextView bill;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main, null);
            ButterKnife.bind(this, view);
            initView();
        }
        ViewGroup group = (ViewGroup) view.getParent();
        if (group != null) {
            group.removeView(view);
        }
        return view;
    }

    private void initView() {
        WindowManager manager = getActivity().getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int layoutWidht = width / 2;
        ViewGroup.LayoutParams params = linearLayout2.getLayoutParams();
        params.height = layoutWidht;
        linearLayout2.setLayoutParams(params);
    }

    @OnClick({R.id.payment, R.id.bill})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.payment:
                startActivity(new Intent(getActivity(), MainPayment.class));
                getActivity().overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
                break;
            case R.id.bill:
                break;
        }
    }
}
