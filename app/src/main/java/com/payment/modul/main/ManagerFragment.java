package com.payment.modul.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.payment.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：Tangren_ on 2017/1/12 17:57.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class ManagerFragment extends Fragment {

    @BindView(R.id.safePsw)
    TextView safePsw;
    @BindView(R.id.paySet)
    TextView paySet;
    @BindView(R.id.exportBill)
    TextView exportBill;
    @BindView(R.id.userInfo)
    TextView userInfo;
    @BindView(R.id.yimafu)
    TextView yimafu;
    @BindView(R.id.user)
    TextView user;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_manager, null);
            ButterKnife.bind(this, view);
        }
        ViewGroup group = (ViewGroup) view.getParent();
        if (group != null) {
            group.removeView(view);
        }


        return view;
    }

    @OnClick({R.id.safePsw, R.id.paySet, R.id.exportBill, R.id.userInfo, R.id.yimafu, R.id.user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.safePsw:
                Toast.makeText(getActivity(), "safePsw", Toast.LENGTH_SHORT).show();
                break;
            case R.id.paySet:
                Toast.makeText(getActivity(), "paySet", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exportBill:
                Toast.makeText(getActivity(), "exportBill", Toast.LENGTH_SHORT).show();
                break;
            case R.id.userInfo:
                Toast.makeText(getActivity(), "userInfo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.yimafu:
                Toast.makeText(getActivity(), "yimafu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user:
                Toast.makeText(getActivity(), "User", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
