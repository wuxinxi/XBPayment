package com.payment.modul.bill.presenter;


import com.payment.base.BasePresenter;
import com.payment.entity.Bill;
import com.payment.modul.bill.model.BillCompl;
import com.payment.modul.bill.model.IBillModel;
import com.payment.modul.bill.view.IBillView;

import java.util.List;

/**
 * 作者：Tangren_ on 2016/12/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class BillPresenter extends BasePresenter<IBillView> {
    private IBillView billView;
    private IBillModel model = new BillCompl();

    public BillPresenter(IBillView billView) {
        this.billView = billView;
    }

    public void fetch(String begainTime, String endTime) {
        if (model != null) {
            model.loadBillList(begainTime, endTime, new IBillModel.LoadListener() {
                @Override
                public void onSuccessShow(List<Bill.VarListBean> varListBeen) {
                    if (billView != null) {
                        billView.onSuccess(varListBeen);
                    }
                }

                @Override
                public void onFailShow(String msg) {
                    if (billView != null) {
                        billView.onFail(msg);
                    }
                }
            });
        }
    }
}
