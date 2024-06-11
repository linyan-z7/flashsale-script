package cn.flashsale.script.common.jd;


import cn.flashsale.script.common.property.SeleniumProperty;
import cn.flashsale.script.common.selenium.SeleniumDrivesSettingOptions;
import cn.flashsale.script.common.selenium.SeleniumTools;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.google.common.collect.Lists;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class JdMain {

    public static void main(String[] args) throws IOException {
        // SeleniumProperty.CHROMEDRIVER
        System.setProperty("webdriver.chrome.driver", SeleniumProperty.CHROMEDRIVER);
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

        // 检测登录状态，120秒
        int i = 1;
        while (i <= 120) {
            String now = DateUtil.now();
            if (JdTools.isLogin(driver)) {
                System.out.println(now + "扫码登录成功！");
                i = 999;
                if (FileUtil.isFile(screenshotPath)) {
                    FileUtil.del(screenshotPath);
                }
            } else {
                System.err.println(now + "未登录...");
            }
            try {
                Thread.sleep(1500);
            } catch (Exception ignored) {
            }
            i++;
        }
        if (i != 999) {
            System.err.println("您长时间未扫码，程序结束！");
            driver.close();
        }


        /*ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
        }, 0, 1, TimeUnit.SECONDS);*/
        System.out.println("ok!");
        driver.close();
    }

    /*public static void toLoginUrl(){
        Actions actions = new Actions(driver);
    }*/

}
