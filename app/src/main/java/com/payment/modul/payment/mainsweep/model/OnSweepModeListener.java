package com.payment.modul.payment.mainsweep.model;

import com.payment.entity.PayInfo;

/**
 * 作者：Tangren_ on 2017/1/14 15:46.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public interface OnSweepModeListener {
    void fetchSweep(PayInfo info, SweepListener listener);

    interface SweepListener {
        void onResult(int code, String payUrl);
    }

    void onCheckInfo(int code, String msg, SweepCheckListener listener);

    interface SweepCheckListener {
        void onCheckInfo(int code, String msg);
    }

}
