package com.payment.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：Tangren_ on 2017/1/13 17:25.
 * 邮箱：wu_tangren@163.com
 * TODO:支付信息
 */

public class PayInfo implements Parcelable {
    private String type;
    private String orderNo;
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PayInfo() {
    }

    public PayInfo(String type, String orderNo, String amount) {
        this.type = type;
        this.orderNo = orderNo;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PayInfo{" +
                "type='" + type + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.orderNo);
        dest.writeString(this.amount);
    }

    protected PayInfo(Parcel in) {
        this.type = in.readString();
        this.orderNo = in.readString();
        this.amount = in.readString();
    }

    public static final Parcelable.Creator<PayInfo> CREATOR = new Parcelable.Creator<PayInfo>() {
        @Override
        public PayInfo createFromParcel(Parcel source) {
            return new PayInfo(source);
        }

        @Override
        public PayInfo[] newArray(int size) {
            return new PayInfo[size];
        }
    };
}
