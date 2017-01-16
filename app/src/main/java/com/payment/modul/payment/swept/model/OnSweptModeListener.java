package com.payment.modul.payment.swept.model;

import com.payment.entity.PayInfo;

/**
 * 作者：Tangren_ on 2017/1/13 15:29.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public interface OnSweptModeListener {
    //被扫请求
    void fatchSwept(String auth_code, PayInfo info, SweptListener listener);

    interface SweptListener {
        void onSuccess(String msg);

        void onFail(int code, String msg);
    }

    //被扫检查订单
    void fetchCheck(String orderNo, CheckListener listener);

    interface CheckListener {
        void onCheckInfo(int code, String msg);
    }


}
