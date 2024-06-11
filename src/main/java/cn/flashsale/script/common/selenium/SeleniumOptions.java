package cn.flashsale.script.common.selenium;


public enum SeleniumOptions {

    ALLOW_CROSS_DOMAIN("允许任何来源的远程连接，避免一些跨域问题",
                               "--remote-allow-origins=*"),

    ANTI_DETECTION("(防止爬虫策略)当一个网页检测到这个特性被启用时，它可能会认为这个页面是被自动化工具控制的，而不是一个真实的用户。因此，通过禁用这个特性，你可以让你的自动化测试更像一个真实的用户",
                           "--disable-blink-features=AutomationControlled"),

    WINDOWS10_USER_AGENT("Windows10用户代理",
                                 "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36"),

    LINUX_USER_AGENT("Linux用户代理",
                             "--user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36"),

    CLOSE_WINDOWS_SHOW("关闭窗口显示（推荐在服务器环境使用）",
                         "--headless"),

    // 在无窗口模式下，，且在服务器上运行，建议使用该配置！！！
    SET_WINDOW_SIZE("(防止爬虫策略)设置窗口大小为：1024*768",
                            "--window-size=1024,768"),

    DISABLE_GPU("禁用GPU",
                        "--disable-gpu"),

    NO_SANDBOX("禁用 Chrome 的沙箱模式。在某些环境中，例如 Docker，沙箱模式可能会导致问题"
                       , "--no-sandbox"),

    DISABLE_DEV_SHM_USAGE("禁止使用 /dev/shm 共享内存，而改为使用 /tmp 目录",
                                  "--disable-dev-shm-usage"),

    INCOGNITO_MODEL("以隐私模式启动 Chrome",
                            "--incognito"),

    DISABLE_EXTENSIONS("禁用所有 Chrome 扩展",
                               "--disable-extensions");

    SeleniumOptions(String describe, String value) {
        this.describe = describe;
        this.value = value;
    }
    public String describe;
    public String value;

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setValue(String value) {
        this.value = value;
    }
}