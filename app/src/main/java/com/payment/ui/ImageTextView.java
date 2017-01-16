package com.payment.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 作者：Tangren_ on 2016/12/17 20:55.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class ImageTextView extends TextView {

    private Drawable[] drawables;

    private int width;

    private int height;

    public ImageTextView(Context context) {
        super(context);
        init();
    }


    public ImageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        drawables = getCompoundDrawables();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int specMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int specSize = View.MeasureSpec.getSize(widthMeasureSpec);
        switch (specMode) {
            case MeasureSpec.EXACTLY:
                width = specSize;
                break;
            case MeasureSpec.AT_MOST:
                break;
        }
        specMode = View.MeasureSpec.getMode(heightMeasureSpec);
        specSize = View.MeasureSpec.getSize(heightMeasureSpec);
        switch (specMode) {
            case MeasureSpec.EXACTLY:
                height = specSize;
                break;
            case MeasureSpec.AT_MOST:
                break;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (null != drawables) {
            Drawable drawableLeft = drawables[0];
            Drawable drawableTop = drawables[1];
            Drawable drawableRight = drawables[2];
            Drawable drawableBottom = drawables[3];
            //文字宽度
            float textWidth = getPaint().measureText(getText().toString());
            float textHeight = height;
            if (null != drawableLeft) {
                setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
                //内容区域
                float contentWidth = textWidth + getCompoundDrawablePadding() + drawableLeft.getIntrinsicWidth();
                //向X轴的正方向平移
                canvas.translate((getWidth() - contentWidth) / 2, 0);
            }
            if (null != drawableRight) {
                //xml中不必设置Gravity，右边图片必须要设置Graviey为End，否则translate后文字看不到
                setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
                float contentWidth = textWidth + getCompoundDrawablePadding() + drawableRight.getIntrinsicWidth();
                //向X轴的负方向平移
                canvas.translate(-(getWidth() - contentWidth) / 2, 0);
            }
            if (null != drawableTop) {
                setGravity(Gravity.TOP | Gravity.CENTER);
                float contentHeight = drawableTop.getIntrinsicHeight();
                canvas.translate(0, getHeight() / 2 - contentHeight / 2);
//                canvas.translate(0, (getHeight() - textHeight / 2) / 2);
            }
            if (null != drawableBottom) {

            }
        }

        super.onDraw(canvas);
    }
}
