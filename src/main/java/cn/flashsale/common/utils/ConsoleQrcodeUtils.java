package cn.flashsale.common.utils;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.Serializable;
import java.util.HashMap;

public class ConsoleQrcodeUtils {

    public static void outQrcode(String content) {

        int width = 1; // 二维码宽度
        int height = 1; // 二维码高度

        // 定义二维码的参数
        HashMap<EncodeHintType, Serializable> hints = new HashMap<EncodeHintType, java.io.Serializable>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");//编码方式
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//纠错等级

        // 打印二维码
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            for (int j = 0; j < bitMatrix.getHeight(); j++) {
                for (int i = 0; i < bitMatrix.getWidth(); i++) {
                    if (bitMatrix.get(i, j)) {
                        System.out.print("■");
                    } else {
                        System.out.print("  ");
                    }

                }
                System.out.println();
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
