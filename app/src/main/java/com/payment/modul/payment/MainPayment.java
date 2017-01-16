package com.payment.modul.payment;

import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.payment.R;
import com.payment.base.BaseNormalActivity;
import com.payment.entity.PayInfo;
import com.payment.modul.payment.confirm.OrderActivity;
import com.payment.ui.TypePopwin;
import com.payment.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.payment.R.id.pay_type;

/**
 * 作者：Tangren_ on 2017/1/13 11:51.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class MainPayment extends BaseNormalActivity {
    @BindView(R.id.mtoolbar)
    Toolbar mtoolbar;
    @BindView(R.id.amount)
    EditText amount;
    @BindView(pay_type)
    TextView payType;
    @BindView(R.id.linearLayout_update_paytype)
    LinearLayout linearLayoutUpdatePaytype;
    @BindView(R.id.clear)
    Button clear;
    @BindView(R.id.num_del)
    Button numDel;
    @BindView(R.id.determain)
    Button determain;

    private PayInfo info;
    private StringBuilder pType = new StringBuilder("微信");
    private TypePopwin popwin;
    private Button temp;
    private int count = 0;
    private int index = 0;
    private int[] buttons = new int[]{R.id.num_0, R.id.num_1, R.id.num_2,
            R.id.num_3, R.id.num_4, R.id.num_5,
            R.id.num_6, R.id.num_7, R.id.num_8,
            R.id.num_9};

    @Override
    public int initLayout() {
        return R.layout.activity_mainpayment;
    }

    @Override
    public void initView() {
        mtoolbar.setTitle("收款");
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        amount.setText("0.00");
        amount.setSelection(4);
        for (int i = 0; i < buttons.length; i++) {
            temp = (Button) findViewById(buttons[i]);
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inputNum(String.valueOf(((Button) v).getText()));
                }
            });
        }

        popwin = new TypePopwin(this, listener);
        info = new PayInfo();
    }

    @OnClick({R.id.linearLayout_update_paytype, R.id.clear, R.id.num_del, R.id.determain})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearLayout_update_paytype:
                View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
                popwin.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.clear:
                amount.setText("0.00");
                break;
            case R.id.num_del:
                delNum();
                break;
            case R.id.determain:
                if (amount.getText().toString().equals("0.00"))
                    Toast.makeText(this, "请输入收款金额", Toast.LENGTH_SHORT).show();
                else {
                    info = new PayInfo(pType.toString(), Utils.OrderNo(), amount.getText().toString().trim());
                    OrderActivity.start(MainPayment.this, info);
                    overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
                }
                break;
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.wechat_pay:
                    pType.replace(0, pType.length(), "微信");
                    break;
                case R.id.ten_pay:
                    pType.replace(0, pType.length(), "QQ");
                    break;
                case R.id.ali_pay:
                    pType.replace(0, pType.length(), "支付宝");
                    break;
                default:
                    pType.replace(0, pType.length(), "微信");
                    break;
            }
            payType.setText(pType.toString());
            popwin.dismiss();
        }
    };

    private void inputNum(String num) {
        String result = amount.getText().toString().trim() + num;
        result = result.replace(".", "");
        if (count < 3 && index == result.length() && result.startsWith("0")) {
            result = result.substring(1, result.length());
        }
        String a = String.valueOf(Long.valueOf(result.substring(0, result.length() - 2)));//整数部分
        StringBuilder sb = new StringBuilder(a);
        sb.append(".").append(result.subSequence(result.length() - 2, result.length()));//小数部分
        String tem = sb.toString();
        if (tem.length() > 10)
            return;
        amount.setText(tem);
        amount.setSelection(tem.length());
        count++;
    }

    private void delNum() {
        if (count != 0) {
            String result = amount.getText().toString().trim();
            result = result.replace(".", "");
            if (result.length() == 3) {
                result = "0".concat(result);
            }
            StringBuilder sb = new StringBuilder(result.substring(0, result.length() - 1));
            sb.insert(sb.toString().length() - 2, ".");
            amount.setText(sb.toString());
            amount.setSelection(sb.length());
            count--;
        } else {
            amount.setText("0.00");
            amount.setSelection(4);
        }
    }
}
