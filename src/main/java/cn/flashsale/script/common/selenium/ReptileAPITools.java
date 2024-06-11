package cn.flashsale.script.common.selenium;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ReptileAPITools {

    @SuppressWarnings("unchecked")
    public static Map<String, String> foramtObjectToParamsStrMap(Object object) {
        if (object == null) {
            return new HashMap<String, String>();
        }
        String jsonStr = JSON.toJSONString(object, SerializerFeature.EMPTY);
        return (Map<String, String>) JSON.parseObject(jsonStr, Map.class);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> foramtObjectToParamsObjMap(Object object) {
        if (object == null) {
            return new HashMap<>();
        }
        String jsonStr = JSON.toJSONString(object, SerializerFeature.EMPTY);
        return (HashMap<String, Object>) JSON.parseObject(jsonStr, HashMap.class);
    }

    public static String formatMapToUrlParams(Map<String, Object> params) {
        return formatMapToUrlParams(params, false);
    }

    public static String formatMapToUrlParams(Map<String, Object> params, boolean encode) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        StringBuilder strb = new StringBuilder();
        int index = 0;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (index == 0) {
                strb.append("?");
            } else {
                strb.append("&");
            }
            strb.append(entry.getKey()).append("=");
            Object value = entry.getValue();
            if (encode && value != null) {
                try {
                    strb.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
                } catch (Exception ignored) {
                }
            } else {
                strb.append(entry.getValue());
            }
            index++;
        }
        return strb.toString();
    }

    public static Map<String, String> headers() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-cn,zh;q=0.8");
        headers.put("Accept-Charset", "zh-CN,zh;q=0.9");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
        return headers;
    }

}
