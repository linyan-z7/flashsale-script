package cn.flashsale.core.selenium.exception;

public class ReptileDataFormatException extends ReptileException {

    private ReptileDataFormatException() {
        super();
    }

    public ReptileDataFormatException(String message) {
        super(message);
    }

    public ReptileDataFormatException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public static void throwe(String errorMessage) {
        throw new ReptileDataFormatException(errorMessage);
    }

    public static void throwe(Throwable e) {
        throw new ReptileDataFormatException("爬取数据格式化异常", e);
    }
}
