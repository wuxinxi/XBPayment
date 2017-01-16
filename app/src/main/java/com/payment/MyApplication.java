package com.payment;

import android.app.Application;

import com.payment.config.Config;
import com.payment.utils.DBUtils;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.cache.DiskCacheStore;

/**
 * 作者：Tangren_ on 2017/1/12 15:39.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private DBUtils dbUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Logger.setDebug(true);
        Logger.setTag("PaymentDebug___日志");
        NoHttp.initialize(this, new NoHttp.Config()
                .setCacheStore(new DiskCacheStore(this))
                .setNetworkExecutor(new OkHttpNetworkExecutor()));
        dbUtils = new DBUtils();
    }

    public static MyApplication getInstance() {
        if (instance == null) {
            synchronized (MyApplication.class) {
                if (instance == null)
                    instance = new MyApplication();
            }
        }
        return instance;
    }

    public String mch_id() {
        return (String) dbUtils.get(this, "mch_id", Config.MCH_ID);
    }
}
