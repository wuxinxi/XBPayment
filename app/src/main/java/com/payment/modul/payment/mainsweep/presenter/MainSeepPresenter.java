package com.payment.modul.payment.mainsweep.presenter;

import com.payment.base.BasePresenter;
import com.payment.entity.PayInfo;
import com.payment.modul.payment.mainsweep.model.MainSweepCompl;
import com.payment.modul.payment.mainsweep.model.OnSweepModeListener;
import com.payment.modul.payment.mainsweep.view.OnMainSweepListener;

/**
 * 作者：Tangren_ on 2017/1/14 15:51.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class MainSeepPresenter extends BasePresenter<OnMainSweepListener> {
    private OnMainSweepListener listener;
    private OnSweepModeListener modeListener = new MainSweepCompl();

    public MainSeepPresenter(OnMainSweepListener listener) {
        this.listener = listener;
    }

    public void fetchMainSweep(PayInfo info) {
        if (modeListener != null) {
            listener.showLoading();
            modeListener.fetchSweep(info, new OnSweepModeListener.SweepListener() {
                @Override
                public void onResult(int code,String payUrl) {
                    if (listener != null) {
                        listener.closeLoading();
                        listener.onResult(code,payUrl);
                    }
                }
            });
        }
    }
}
