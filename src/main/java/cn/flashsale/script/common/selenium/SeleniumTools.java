package cn.flashsale.script.common.selenium;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class SeleniumTools {

    public static void implicitlyWait(WebDriver driver, long seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public static void close(WebDriver driver) {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {
            }
        }
    }

    public static void wait(WebDriver driver, By by, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(seconds, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static Map<String, String> getCookieMap(WebDriver driver) {
        try {
            Set<Cookie> cookies = driver.manage().getCookies();
            if (cookies == null || cookies.isEmpty()) {
                return new HashMap<String, String>();
            }
            return cookies.stream()
                    .collect(Collectors.toMap(Cookie::getName, Cookie::getValue));
        } catch (Exception e) {
            return new HashMap<String, String>();
        }
    }

    public static Set<Cookie> getCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public static List<String> getCookiesName(WebDriver driver) {
        Set<Cookie> cookies = driver.manage().getCookies();
        if (cookies == null || cookies.isEmpty()) {
            return new ArrayList<String>();
        }
        return cookies.stream().map(Cookie::getName).collect(Collectors.toList());
    }

    public static Cookie JSESSIONID(WebDriver driver) {
        String sessionId = getSessionId(driver);
        if (!sessionId.isEmpty()) {
            return new Cookie("JSESSIONID", sessionId.toUpperCase());
        }
        return null;
    }

    public static String getSessionId(WebDriver driver) {
        try {
            return ((ChromeDriver) driver).getSessionId().toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getUserAgent(WebDriver driver) {
        // 注意！如果驱动配置了--user-agent，则取到的是驱动配置的user-agent
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            return (String) executor.executeScript("return navigator.userAgent;");
        } catch (Exception e) {
            return "";
        }
    }

    public static String getHtmlText(String htmlStr) {
        Document document = Jsoup.parse(htmlStr);
        return document.text();
    }

    public static String getHtml(WebElement element) {
        return element.getAttribute(HtmlElement.Attr.HTML_CONTENT);
    }

    public static String getLabelAttr(WebElement element, String attr) {
        return element.getAttribute(attr);
    }

    public static boolean survival(WebDriver driver) {
        if (driver == null) {
            return false;
        }
        try {
            String handle = driver.getWindowHandle();
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
