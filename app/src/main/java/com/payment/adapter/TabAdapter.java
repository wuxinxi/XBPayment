package com.payment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/1/12 18:51.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class TabAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public TabAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
