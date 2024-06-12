package cn.flashsale.script.damai;

import cn.hutool.core.util.StrUtil;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.Location;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

/**
 * description...
 *
 * @author linyan-z7
 * @date 2024/6/12
 */
public class DamaiMain {

    public static void main(String[] args) throws Exception {
        /*DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "9");*/

        //capabilities.setCapability("app", "/path/to/your/app.apk");
        //capabilities.setCapability("automationName", "uiautomator2");


        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554")
                .setPlatformName("Android")
                .setPlatformVersion("9")
        ;

        AndroidDriver driver = null;
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }


        try {
            // 回到主页，点击指定应用
            driver.pressKey(new KeyEvent(AndroidKey.HOME));
            WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"雷电游戏中心\"]"));
            element.click();

            // 等待搜索框元素可见（最长等待时间为 5 秒），然后搜索应用
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement search_layout = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.id("com.android.flysilkworm:id/search_layout")));
            search_layout.click();
            WebElement base_search = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.id("com.android.flysilkworm:id/base_search")));
            String text = base_search.getText();
            if (StrUtil.isNotEmpty(text)){
                base_search.clear();
            }
            base_search.sendKeys("王者荣耀");
            driver.pressKey(new KeyEvent(AndroidKey.ENTER));


            System.out.println();
        } finally {
            driver.quit();
        }

    }
}
