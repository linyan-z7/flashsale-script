package cn.flashsale.script.common.jd;

import cn.flashsale.script.common.selenium.HtmlElement;
import cn.flashsale.script.common.selenium.SeleniumTools;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * description...
 *
 * @author linyan-z7
 * @date 2024/6/11
 */
public class JdTools {

    public static boolean isLogin(WebDriver driver) {
        Set<Cookie> cookies = SeleniumTools.getCookies(driver);
        int i = 0;
        for (Cookie cookie : cookies) {
            Date now = new Date();
            if (("pin".equals(cookie.getName()) || "thor".equals(cookie.getName()) && DateUtil.compare(now, cookie.getExpiry()) < 0)) {
                i++;
            }
            if (i > 0) {
                return true;
            }
        }
        return false;
    }

    public static void toLoginQrcodePage(WebDriver driver) {
        driver.get("https://passport.jd.com/new/login.aspx?ReturnUrl=https%3A%2F%2Fwww.jd.com%2F");
        SeleniumTools.implicitlyWait(driver, 5);
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(By.className("login-tab-l")));
        actions.perform();
        SeleniumTools.implicitlyWait(driver, 5);
    }

    public static String getLoginQrcode(WebDriver driver) {
        // 获取登录二维码 // <p class="err-cont">二维码已失效</p>
        driver.get("https://passport.jd.com/new/login.aspx?ReturnUrl=https%3A%2F%2Fwww.jd.com%2F");
        SeleniumTools.implicitlyWait(driver, 5);
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(By.className("login-tab-l")));
        actions.perform();
        SeleniumTools.implicitlyWait(driver, 5);
        WebElement loginQrcodeElement = driver.findElement(By.id("passport-main-qrcode-img"));
        return SeleniumTools.getLabelAttr(loginQrcodeElement, HtmlElement.Attr.SRC);
    //    // 若未登录，则获取登录二维码，并保存到本地
    //    if (!JdTools.isLogin(driver)) {
    //        String qrcodeUrl = JdTools.getLoginQrcode(driver);
    //        byte[] bytes = HttpUtil.downloadBytes(qrcodeUrl);
    //        String outQrcodePath = "Z:\\qrcode-" + DateUtil.format(new Date(), "yyyyMMdd-HHmmss") + ".png";
    //        FileUtil.writeBytes(bytes, new File(outQrcodePath));
    //    }
    }
}
