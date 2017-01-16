package com.payment.httpclient;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.download.DownloadQueue;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;

public class CallServer {

    private static CallServer httpclient;

    // 请求队列
    private RequestQueue requestQueuequeue;

    // 下载队列
    private static DownloadQueue downloadQueue;

    private CallServer() {
        requestQueuequeue = NoHttp.newRequestQueue();
    }

    //下载队列.
    public static DownloadQueue getDownloadInstance() {
        if (downloadQueue == null)
            downloadQueue = NoHttp.newDownloadQueue(2);
        return downloadQueue;
    }

    public synchronized static CallServer getHttpclient() {
        if (httpclient == null)
            httpclient = new CallServer();
        return httpclient;
    }

    // 添加一个请求到队列
    public <T> void add(int what, Request<T> request,
                        HttpListener<T> callback) {
        requestQueuequeue.add(what, request, new HttpResponseListener<T>
                (request, callback));
    }

    // 取消队列中所有请求
    public void cancelAll() {
        requestQueuequeue.cancelAll();
    }

    // 退出App时停止所有请求
    public void stopAll() {
        requestQueuequeue.stop();
    }


}
