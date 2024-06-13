package cn.flashsale.component.ocr.tess4j;

import cn.flashsale.component.ocr.OCR;
import com.alibaba.fastjson.JSONArray;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * description...
 *
 * @author linyan-z7
 * @date 2024/6/13
 */
public class Tess4jOCR implements OCR {

    private Tesseract tesseract;

    private Tess4jOCR(){
    }

    public Tess4jOCR(Tesseract tesseract){
        this.tesseract = tesseract;
    }

    @Override
    public JSONArray recognition(String image) {

        return null;
    }

    public static void main(String[] args) throws IOException, TesseractException {
        InputStream is = new FileInputStream("Z://wait.png");
        BufferedImage bufferedImage = ImageIO.read(is);

        Tesseract tact = new Tesseract();
        tact.setDatapath(""); // 训练数据文件夹路径
        tact.setLanguage("chi_sim"); // 设置为中文简体
        String ocrText = tact.doOCR(bufferedImage);

        System.out.println(ocrText);

    }
}
