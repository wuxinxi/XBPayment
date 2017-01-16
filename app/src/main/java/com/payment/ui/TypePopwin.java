package com.payment.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.payment.R;

/**
 * 作者：Tangren_ on 2017/1/13 16:29.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class TypePopwin extends PopupWindow {

    private LinearLayout type_wechat, type_qq, type_ali;
    private TextView cancel;
    private Context context;
    private View view;
    private LayoutInflater inflater;

    public TypePopwin(Context context, View.OnClickListener listener) {
        super(context);
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.popupwindow_view, null);
        type_wechat = (LinearLayout) view.findViewById(R.id.wechat_pay);
        type_qq = (LinearLayout) view.findViewById(R.id.ten_pay);
        type_ali = (LinearLayout) view.findViewById(R.id.ali_pay);
        cancel = (TextView) view.findViewById(R.id.cancel);

        type_wechat.setOnClickListener(listener);
        type_qq.setOnClickListener(listener);
        type_ali.setOnClickListener(listener);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.AnimBotom);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height)
                        dismiss();
                }
                return false;
            }
        });

    }
}
