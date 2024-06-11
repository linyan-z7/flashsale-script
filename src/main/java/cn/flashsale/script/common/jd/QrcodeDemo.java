package cn.flashsale.script.common.jd;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.http.HttpUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.net.URLConnection;

/**
 * description...
 *
 * @author linyan-z7
 * @date 2024/6/11
 */
public class QrcodeDemo {

    public static void main(String[] args) {
        String qrcodeUrl = "https://qr.m.jd.com/show?appid=133&size=147&t=1718091995002";
        byte[] bytes = HttpUtil.downloadBytes(qrcodeUrl);
        FileUtil.writeBytes(bytes, new File("Z:\\qrcode.png"));


        //ConsoleQrcodeUtils.outQrcode("https://qr.m.jd.com/show?appid=133&size=147&t=1718091995002");
    }
}
