package cn.flashsale.core.selenium;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;

@Slf4j
@Configuration
public class SeleniumDriverConfig implements EnvironmentAware {

    @Resource
    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    @PostConstruct
    public void chromeDriverOptions() throws Exception {
        String driverPath = env.getProperty("selenium.chrome-driver-path");
        if (StrUtil.isEmpty(driverPath)) {
            throw new RuntimeException("请指定selenium驱动路径");
        }
        File file = new File(driverPath);
        if (!file.exists() || file.isDirectory()) {
            throw new RuntimeException("selenium驱动不存在");
        }

        System.setProperty("webdriver.chrome.driver", driverPath);
        System.setProperty("webdriver.chrome.whitelistedIps", "");

        String profile = env.getActiveProfiles()[0];
        switch (profile) {
            case "dev": // 开发环境
                SeleniumDrivesSettingOptions.setEnvOptions(
                        SeleniumOptions.CLOSE_WINDOWS_SHOW,
                        SeleniumOptions.WINDOWS10_USER_AGENT,
                        SeleniumOptions.SET_WINDOW_SIZE
                );
                break;
            case "test": // 测试环境
                SeleniumDrivesSettingOptions.setEnvOptions(
                        SeleniumOptions.CLOSE_WINDOWS_SHOW,
                        SeleniumOptions.WINDOWS10_USER_AGENT,
                        SeleniumOptions.SET_WINDOW_SIZE,

                        SeleniumOptions.DISABLE_GPU,
                        SeleniumOptions.NO_SANDBOX,
                        SeleniumOptions.DISABLE_DEV_SHM_USAGE
                );
                break;
        }
    }

}
