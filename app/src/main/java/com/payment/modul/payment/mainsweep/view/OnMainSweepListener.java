package com.payment.modul.payment.mainsweep.view;

import com.payment.base.BasePresenter;
import com.payment.base.BaseView;

/**
 * 作者：Tangren_ on 2017/1/14 14:19.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public interface OnMainSweepListener extends BaseView {

    void onResult(int code, String payUrl);

    void onCheckInfo(int code, String msg);

    class Generl {
        public OnMainSweepListener getDefault() {
            return new OnMainSweepListener() {
                @Override
                public void onResult(int code, String payUrl) {

                }

                @Override
                public void onCheckInfo(int code, String msg) {

                }

                @Override
                public void showLoading() {

                }

                @Override
                public void closeLoading() {

                }

                @Override
                public BasePresenter getPresenter() {
                    return null;
                }
            };
        }
    }
}
