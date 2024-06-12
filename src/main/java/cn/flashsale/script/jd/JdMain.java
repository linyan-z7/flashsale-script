package cn.flashsale.script.jd;


import cn.flashsale.selenium.SeleniumLocalProperties;
import cn.flashsale.selenium.SeleniumDrivesSettingOptions;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.File;
import java.util.Date;

public class JdMain {

    public static void main(String[] args) throws IOException {
        // SeleniumProperty.CHROMEDRIVER
        System.setProperty("webdriver.chrome.driver", SeleniumLocalProperties.CHROME_DRIVER);
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        ChromeDriver driver = new ChromeDriver(SeleniumDrivesSettingOptions.getLocalWin10Options());

        // 若未登录，则获取登录扫码界面的截图
        String screenshotPath = "";
        if (!JdTools.isLogin(driver)) {
            JdTools.toLoginQrcodePage(driver);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            screenshotPath = "Z:\\qrcode-" + DateUtil.format(new Date(), "yyyyMMdd-HHmmss") + ".png";
            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
        }

        // 检测登录状态
        int i = 1;
        int seconds = 15;
        try {
            while (i <= seconds) {
                String now = DateUtil.now();
                if (JdTools.isLogin(driver)) {
                    System.out.println(now + "扫码登录成功！");
                    i = seconds + 1;
                } else {
                    System.err.println(now + "未登录...");
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ignored) {
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (i != seconds + 1) {
                System.err.println("您长时间未扫码，程序结束！");
            }
            if (FileUtil.exist(screenshotPath) && FileUtil.isFile(screenshotPath)) {
                FileUtil.del(screenshotPath);
            }
        }
        driver.close();
    }

    /*public static void toLoginUrl(){
        Actions actions = new Actions(driver);
    }*/

}
