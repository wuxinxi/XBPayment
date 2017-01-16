package com.payment.utils;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：Tangren_ on 2017/1/13 17:56.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class Utils {
    private static DecimalFormat decimalFormat = new DecimalFormat("######0.00");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 订单号
     *
     * @return
     */
    public static String OrderNo() {
        Date date = new Date();
        StringBuilder builder = new StringBuilder("gas");
        builder.append(sdf.format(date));
        return builder.append(buildRandom(3)).toString();
    }

    private static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1)
            random = random + 0.1;
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) (random * num);
    }

    /**
     * 元2分
     *
     * @param amount
     * @return
     */
    public static String Yuan2Fen(String amount) {
        float f = Float.valueOf(amount) * 100;
        return decimalFormat.format(f);
    }

    /**
     * 支付状态
     *
     * @param state
     * @return
     */
    public static String payState(String state) {
        String payState = null;
        switch (state) {
            case "0":
                payState = "支付中";
                break;
            case "1":
                payState = "支付成功";
                break;
            case "2":
                payState = "支付失败";
                break;
            case "3":
                payState = "已冲正";
                break;
            case "4":
                payState = "已退款";
                break;
            case "5":
                payState = "退款";
                break;
            default:
                payState = "未知";
                break;
        }
        return payState;
    }

    public static String getPayType(String s) {
        switch (s) {
            case "微信":
                return s = "1";
            case "QQ":
                return s = "3";
            case "支付宝":
                return s = "3";
        }
        return "1";
    }


    /**
     * 生成二维码
     *
     * @param str
     * @return
     * @throws WriterException
     */
    public static Bitmap CreateCode(String str) throws WriterException {
        Bitmap bitmap;
        BitMatrix matrix = new MultiFormatWriter().encode(str,
                BarcodeFormat.QR_CODE, 300, 300);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        // 二维矩阵转为一维像素数组,也就是一直横着排了
        int[] pix = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pix[y * width + x] = 0xff000000;
                }
            }
        }
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pix, 0, width, 0, 0, width, height);
        return bitmap;

    }
}
