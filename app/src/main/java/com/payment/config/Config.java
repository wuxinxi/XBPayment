package com.payment.config;

/**
 * 作者：Tangren_ on 2017/1/14 11:01.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class Config {

    //小额接口
    public static final String SP_URL = "http://112.74.102.125/bippay/interaction/payment";

    //扫码接口
    public static final String SCP_URL = "http://112.74.102.125/bippay/interaction/scanpay";

    //订单检查
    public static final String CEHCK_URL = "http://112.74.102.125/bippay/interaction/queryorder";

    //收款方式
    public static final String PAYWAY = "scan";

    //交易类型
    public static final String TRANTTYPE = "0";

    public static final String MCH_ID = "100100100007";


}
