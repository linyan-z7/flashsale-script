package cn.flashsale.selenium.exception;

public class ReptileHttpRequestException extends ReptileException {

    private ReptileHttpRequestException() {
    }

    private ReptileHttpRequestException(String message) {
        super(message);
    }

    private ReptileHttpRequestException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public static void throwe(String errorMessage) {
        throw new ReptileHttpRequestException(errorMessage);
    }

    public static void throwe(Throwable e) {
        throw new ReptileHttpRequestException("爬取数据Http请求异常", e);
    }
}
