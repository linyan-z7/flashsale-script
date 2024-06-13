package cn.flashsale.core.selenium;

import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumDrivesSettingOptions {

    private static ChromeOptions envOptions;

    public static void setEnvOptions(SeleniumOptions... options) {
        if (envOptions == null) {
            envOptions = new ChromeOptions();
        }
        envOptions.addArguments(SeleniumOptions.ALLOW_CROSS_DOMAIN.value);
        envOptions.addArguments(SeleniumOptions.ANTI_DETECTION.value);
        for (SeleniumOptions seleniumOptions : options) {
            envOptions.addArguments(seleniumOptions.value);
        }
    }

    public static ChromeOptions getEnvOptions() {
        return envOptions;
    }

    public static ChromeOptions getLocalWin10Options() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(SeleniumOptions.WINDOWS10_USER_AGENT.value)
                .addArguments(SeleniumOptions.SET_WINDOW_SIZE.value)
                .addArguments(SeleniumOptions.ALLOW_CROSS_DOMAIN.value)
                .addArguments(SeleniumOptions.ANTI_DETECTION.value);
                // TODO 关闭浏览器窗口 .addArguments(SeleniumOptions.CLOSE_WINDOWS_SHOW.value);
        return options;
    }

}