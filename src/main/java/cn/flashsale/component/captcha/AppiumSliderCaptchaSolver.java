package cn.flashsale.component.captcha;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import io.appium.java_client.TouchAction;

import java.time.Duration;
import java.util.Base64;

/**
 * appium滑块验证码处理
 */
public class AppiumSliderCaptchaSolver {

    private AndroidDriver driver;

    private AppiumSliderCaptchaSolver() {
    }

    public AppiumSliderCaptchaSolver(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * 执行
     *
     * @param captchaImgElement  验证码图片元素
     * @param sliderImageElement 滑块图像元素
     */
    @SuppressWarnings("deprecation")
    public void execute(WebElement captchaImgElement, WebElement sliderImageElement) {
        // 获取验证码图片
        //WebElement captchaImage = driver.findElement(By.id("captcha_image_id"));
        String base64Image = captchaImgElement.getScreenshotAs(OutputType.BASE64);
        Mat template = Imgcodecs.imdecode(new MatOfByte(Base64.getDecoder().decode(base64Image)), Imgcodecs.IMREAD_COLOR);

        // 获取滑块图像
        //WebElement sliderImage = driver.findElement(By.id("slider_image_id"));
        String base64Slider = sliderImageElement.getScreenshotAs(OutputType.BASE64);
        Mat slider = Imgcodecs.imdecode(new MatOfByte(Base64.getDecoder().decode(base64Slider)), Imgcodecs.IMREAD_COLOR);

        // 模板匹配
        Point matchLoc = matchTemplate(template, slider);

        // 计算滑动距离
        int startX = sliderImageElement.getLocation().getX();
        int startY = sliderImageElement.getLocation().getY();
        int endX = (int) (startX + matchLoc.x);
        int endY = startY;

        // 执行滑动操作
        new TouchAction<>(this.driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
    }

    private static Point matchTemplate(Mat template, Mat slider) {
        int resultCols = template.cols() - slider.cols() + 1;
        int resultRows = template.rows() - slider.rows() + 1;
        Mat result = new Mat(resultRows, resultCols, CvType.CV_32FC1);

        Imgproc.matchTemplate(template, slider, result, Imgproc.TM_CCOEFF_NORMED);
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
        return mmr.maxLoc;
    }
}
