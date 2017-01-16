package com.payment.modul.bill.view;

import com.payment.base.BasePresenter;
import com.payment.base.BaseView;
import com.payment.entity.Bill;

import java.util.List;

/**
 * 作者：Tangren_ on 2016/12/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public interface IBillView extends BaseView {
    void onSuccess(List<Bill.VarListBean> varListBeen);

    void onFail(String msg);

    class Generator {
        public static IBillView getDefault() {
            return new IBillView() {

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

                @Override
                public void onSuccess(List<Bill.VarListBean> varListBeen) {

                }

                @Override
                public void onFail(String msg) {

                }
            };
        }
    }
}
