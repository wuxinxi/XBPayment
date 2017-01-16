package com.payment.modul.payment.mainsweep.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.payment.R;
import com.payment.base.BaseActivity;
import com.payment.entity.PayInfo;
import com.payment.modul.payment.mainsweep.presenter.MainSeepPresenter;
import com.payment.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Tangren_ on 2017/1/14 15:45.
 * 邮箱：wu_tangren@163.com
 * TODO:主扫
 */

public class MainSweepActivity extends BaseActivity<OnMainSweepListener, MainSeepPresenter> implements OnMainSweepListener {

    @BindView(R.id.mtoolbar)
    Toolbar mtoolbar;
    @BindView(R.id.qrcode)
    ImageView qrcode;
    @BindView(R.id.linear_qrcode)
    LinearLayout linearQrcode;
    @BindView(R.id.swept)
    Button swept;
    @BindView(R.id.checkOrder)
    Button checkOrder;

    private static String KEY = "payInfo";
    private PayInfo info;

    public static void start(Context context, PayInfo info) {
        Intent intent = new Intent(context, MainSweepActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY, info);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        info = getIntent().getParcelableExtra(KEY);
        mtoolbar.setTitle(info.getType());
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WindowManager manager = getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int layout = (int) (width * 0.7);
        ViewGroup.LayoutParams params = linearQrcode.getLayoutParams();
        params.height = layout;
        params.width = layout;
        linearQrcode.setLayoutParams(params);

        info.setOrderNo(Utils.OrderNo());
        mPresenter.fetchMainSweep(info);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_mainsweep;
    }

    @Override
    public void onResult(int code, String payUrl) {
        if (code == 1) {
            Bitmap bitmap = null;
            try {
                bitmap = Utils.CreateCode(payUrl);
            } catch (WriterException e) {
                e.printStackTrace();
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_error);
                Toast.makeText(this, "出现异常!", Toast.LENGTH_SHORT).show();
            }
            qrcode.setImageBitmap(bitmap);
        } else
            Toast.makeText(this, payUrl, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckInfo(int code, String msg) {

    }

    @Override
    protected MainSeepPresenter createPresenter() {
        return new MainSeepPresenter(this);
    }


    @OnClick({R.id.swept, R.id.checkOrder})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.swept:
                break;
            case R.id.checkOrder:
                break;
        }
    }
}
