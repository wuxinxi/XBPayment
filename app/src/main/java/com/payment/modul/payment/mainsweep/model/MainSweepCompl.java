package com.payment.modul.payment.mainsweep.model;

import com.payment.config.Config;
import com.payment.entity.PayInfo;
import com.payment.httpclient.CallServer;
import com.payment.httpclient.HttpListener;
import com.payment.utils.Utils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.CacheMode;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：Tangren_ on 2017/1/14 15:52.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class MainSweepCompl implements OnSweepModeListener {

    @Override
    public void fetchSweep(PayInfo info, SweepListener listener) {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(Config.SCP_URL, RequestMethod.POST);
        request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        request.set("mch_id", Config.MCH_ID);
        request.set("paytype", Utils.getPayType(info.getType()));
        request.set("trantype", "0");
        request.set("orderid", info.getOrderNo());
        CallServer.getHttpclient().add(4, request, new SweepCallBack(listener));
    }

    @Override
    public void onCheckInfo(int code, String msg, SweepCheckListener listener) {

    }


    private class SweepCallBack implements HttpListener<JSONObject> {

        private SweepListener listener;

        public SweepCallBack(SweepListener listener) {
            this.listener = listener;
        }

        @Override
        public void success(int what, Response<JSONObject> response) {
            JSONObject object = response.get();
            try {
                String rescode = object.getString("rescode");
                if (rescode.equals("00")) {
                    listener.onResult(1, object.getString("codeimgurl"));
                } else listener.onResult(2, response.get().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void fail(int what, Response<JSONObject> response) {

        }
    }
}
