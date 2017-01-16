package com.payment.modul.payment.swept.presenter;

import com.payment.base.BasePresenter;
import com.payment.entity.PayInfo;
import com.payment.modul.payment.swept.model.OnSweptModeListener;
import com.payment.modul.payment.swept.model.SweptCompl;
import com.payment.modul.payment.swept.view.OnSweptListener;

/**
 * 作者：Tangren_ on 2017/1/13 15:53.
 * 邮箱：wu_tangren@163.com
 * TODO:被扫交易
 */

public class SweptPresenter extends BasePresenter<OnSweptListener> {
    private OnSweptListener listener;
    private OnSweptModeListener modeListener = new SweptCompl();

    public SweptPresenter(OnSweptListener listener) {
        this.listener = listener;
    }

    public void fatch(String auth_code, PayInfo info) {
        if (modeListener != null) {
            listener.showLoading();
            modeListener.fatchSwept(auth_code, info, new OnSweptModeListener.SweptListener() {
                @Override
                public void onSuccess(String msg) {
                    if (listener != null) {
                        listener.closeLoading();
                        listener.onSuccess(msg);
                    }
                }

                @Override
                public void onFail(int code, String msg) {
                    listener.closeLoading();
                    listener.onFail(code, msg);
                }
            });
        }
    }

    public void checkOrder(String orderNo) {
        if (modeListener != null) {
            listener.showLoading();
            modeListener.fetchCheck(orderNo, new OnSweptModeListener.CheckListener() {
                @Override
                public void onCheckInfo(int code, String msg) {
                    if (listener != null) {
                        listener.closeLoading();
                        listener.onCheckInfo(code, msg);
                    }
                }
            });
        }
    }
}
