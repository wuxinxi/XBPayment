package com.payment.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.payment.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * 作者：Tangren_ on 2017/1/14 10:11.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class Loading extends Dialog {

    private Context context;
    private LayoutInflater inflater;
    private AVLoadingIndicatorView loading;

    public Loading(Context context) {
        super(context, R.style.Custom_Progress);
        this.context = context;
        inflater = inflater.from(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        View view = inflater.inflate(R.layout.loading_view, null);
        loading = (AVLoadingIndicatorView) view.findViewById(R.id.loading);
        setContentView(view);
        getWindow().getAttributes().gravity = Gravity.CENTER;
        loading.show();
    }

    public void stop() {
        if (loading != null) {
            loading.hide();
        }
    }

}
