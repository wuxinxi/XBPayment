package com.payment.base;

/**
 * 作者：Tangren_ on 2017/1/13 11:32.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public interface BaseView {
    void showLoading();

    void closeLoading();

    BasePresenter getPresenter();
}
