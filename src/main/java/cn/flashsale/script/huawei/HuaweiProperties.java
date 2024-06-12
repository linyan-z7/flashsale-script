package cn.flashsale.script.huawei;

import lombok.Getter;

/**
 * description...
 *
 * @author linyan-z7
 * @date 2024/6/12
 */
public class HuaweiProperties {

    @Getter
    public enum PcUrl {
        LOGIN("PC端登录地址", "?");

        PcUrl(String label, String value) {
            this.label = label;
            this.value = value;
        }

        private final String label;
        private final String value;
    }

}
