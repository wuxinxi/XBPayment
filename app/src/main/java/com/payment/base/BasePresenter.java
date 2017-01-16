package com.payment.base;

import java.lang.ref.WeakReference;

/**
 * 作者：Tangren_ on 2017/1/13 11:31.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public abstract class BasePresenter<T> {
    protected WeakReference<T> mViewRef;

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    public void detechView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
