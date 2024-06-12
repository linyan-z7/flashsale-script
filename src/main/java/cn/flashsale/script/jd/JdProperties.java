package cn.flashsale.script.jd;

import lombok.Getter;

public class JdProperties {


    public static class Cookies {
        public static final String COOKIES_PIN = "pin";
        public static final String COOKIES_THOR = "thor";
    }

    @Getter
    public enum PcUrl {
        LOGIN_REDIRECTION_INDEX("PC端登录地址(登录后重定向至首页)", "https://passport.jd.com/new/login.aspx?ReturnUrl=https%3A%2F%2Fwww.jd.com%2F");

        PcUrl(String label, String value) {
            this.label = label;
            this.value = value;
        }

        private final String label;
        private final String value;
    }

}
