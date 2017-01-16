package com.payment.modul.payment.swept.view;

import com.payment.base.BasePresenter;
import com.payment.base.BaseView;

/**
 * 作者：Tangren_ on 2017/1/13 15:25.
 * 邮箱：wu_tangren@163.com
 * TODO:被扫回调
 */

public interface OnSweptListener extends BaseView {
    void onSuccess(String msg);

    void onFail(int code, String msg);

    void onCheckInfo(int code, String msg);

    class Generl {
        public OnSweptListener getDefault() {
            return new OnSweptListener() {
                @Override
                public void onSuccess(String msg) {

                }

                @Override
                public void onFail(int code, String msg) {

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
