package com.payment.modul.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.payment.R;

/**
 * 作者：Tangren_ on 2017/1/12 17:57.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class SettingFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_setting, null);
        }
        ViewGroup group = (ViewGroup) view.getParent();
        if (group != null) {
            group.removeView(view);
        }

        return view;
    }
}
