package com.payment.modul.main;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.payment.R;
import com.payment.adapter.TabAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    @BindView(R.id.viewPage)
    ViewPager viewPage;
    @BindView(R.id.activity_main)
    CoordinatorLayout activityMain;
    @BindView(R.id.tabHost)
    FragmentTabHost tabHost;

    private Class fragmentArray[] = {MainFragment.class, ManagerFragment.class, SettingFragment.class};
    private String title[] = {"首页", "管理", "设置"};
    private int imageArray[] = {R.drawable.main_selector, R.drawable.manager_selector, R.drawable.setting_selector};
    private List<Fragment> list = new ArrayList<Fragment>();
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initPage();
    }

    private void initView() {
        viewPage.addOnPageChangeListener(this);//设置监听切换
        inflater = LayoutInflater.from(this);
        tabHost.setup(this, getSupportFragmentManager(), R.id.viewPage);//绑定ViewPage
        tabHost.setOnTabChangedListener(this);
        int count = title.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec spec = tabHost.newTabSpec(title[i])
                    .setIndicator(getTabItemView(i));
            tabHost.addTab(spec, fragmentArray[i], null);
            tabHost.setTag(i);
        }
    }

    private void initPage() {
        MainFragment mainFragment = new MainFragment();
        ManagerFragment managerFragment = new ManagerFragment();
        SettingFragment settingFragment = new SettingFragment();
        list.add(mainFragment);
        list.add(managerFragment);
        list.add(settingFragment);
        viewPage.setAdapter(new TabAdapter(getSupportFragmentManager(), list));
        tabHost.getTabWidget().setDividerDrawable(null);
    }

    private View getTabItemView(int i) {
        View view = inflater.inflate(R.layout.tab_item_view, null);
        ImageView image = (ImageView) view.findViewById(R.id.imageView);
        TextView text = (TextView) view.findViewById(R.id.textView);
        image.setBackgroundResource(imageArray[i]);
        text.setText(title[i]);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        TabWidget widget = tabHost.getTabWidget();
        int old = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        tabHost.setCurrentTab(position);
        widget.setDescendantFocusability(old);//设置取消分割线
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {
        int postion = tabHost.getCurrentTab();
        viewPage.setCurrentItem(postion);
    }
    long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序！", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
