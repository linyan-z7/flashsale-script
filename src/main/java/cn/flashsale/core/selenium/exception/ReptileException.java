package cn.flashsale.core.selenium.exception;

public class ReptileException extends RuntimeException {

    ReptileException() {
    }

    public ReptileException(String message) {
        super(message);
    }

    public ReptileException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
    public static void throwe(Throwable e) {
        throw new ReptileException("爬取数据异常", e);
    }
}
