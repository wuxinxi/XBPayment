package com.payment.modul.payment.swept.model;

import com.payment.config.Config;
import com.payment.entity.PayInfo;
import com.payment.httpclient.CallServer;
import com.payment.httpclient.HttpListener;
import com.payment.utils.Utils;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.CacheMode;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 作者：Tangren_ on 2017/1/13 15:54.
 * 邮箱：wu_tangren@163.com
 * TODO:被扫:小额支付--小额订单查询
 */

public class SweptCompl implements OnSweptModeListener {

    @Override
    public void fatchSwept(String auth_code, PayInfo info, SweptListener listener) {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(Config.SP_URL, RequestMethod.POST);
        request.setConnectTimeout(5 * 1000);//连接时间5S
        request.setReadTimeout(12 * 10 * 1000);//响应时间2分钟
        request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        request.set("mch_id", Config.MCH_ID);
        request.set("auth_code", auth_code);
        request.set("total_fee", info.getAmount());
        request.set("payway", Config.PAYWAY);
        request.set("trantype", Config.TRANTTYPE);
        CallServer.getHttpclient().add(2, request, new SweptCallBack(listener));
    }

    @Override
    public void fetchCheck(String orderNo, CheckListener listener) {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(Config.CEHCK_URL, RequestMethod.POST);
        request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        request.set("MCHID", Config.MCH_ID);
        request.set("ORDERID", orderNo);
        Logger.d("MCHID:" + Config.MCH_ID);
        Logger.d("ORDERID:" + orderNo);
        CallServer.getHttpclient().add(3, request, new SweptCheckOrder(listener));
    }

    private class SweptCallBack implements HttpListener {

        private SweptListener listener;

        public SweptCallBack(SweptListener listener) {
            this.listener = listener;
        }

        @Override
        public void success(int what, Response response) {
            JSONObject jsonObject = (JSONObject) response.get();
            try {
                String rescode = jsonObject.getString("rescode");
                if (rescode.equals("00"))
                    listener.onSuccess("支付成功");
                else listener.onFail(400, response.get().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void fail(int what, Response response) {
            listener.onFail(202, "超时,请检查支付结果！");
        }
    }


    private class SweptCheckOrder implements HttpListener {
        private CheckListener listener;

        public SweptCheckOrder(CheckListener listener) {
            this.listener = listener;
        }

        @Override
        public void success(int what, Response response) {

            JSONObject jsonObject = (JSONObject) response.get();
            try {
                String rescode = jsonObject.getString("rescode");
                if (rescode.equals("00")) {
                    JSONArray array = jsonObject.getJSONArray("varList");
                    JSONObject object = array.getJSONObject(0);
                    String paysts = object.getString("paysts");
                    listener.onCheckInfo(200, Utils.payState(paysts));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void fail(int what, Response response) {
            listener.onCheckInfo(404, "网络或服务器异常！");
        }
    }
}
