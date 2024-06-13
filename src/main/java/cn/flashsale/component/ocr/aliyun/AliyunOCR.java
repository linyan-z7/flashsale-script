package cn.flashsale.component.ocr.aliyun;


import cn.flashsale.component.ocr.OCRProperties;
import cn.flashsale.component.ocr.OCR;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AliyunOCR implements OCR {

    @Override
    public JSONArray recognition(String image) {
        String url = "https://tysbgpu.market.alicloudapi.com/api/predict/ocr_general";

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + OCRProperties.Aliyun.APP_CODE);
        headers.put("Content-Type", "application/json; charset=UTF-8");

        Map<String, Object> cm = new HashMap<>();
        cm.put("min_size", 16); // 图片二进制数据的base64编码/图片url
        cm.put("output_prob", true); // 是否输出文字框的概率
        cm.put("output_keypoints", false); // 是否输出文字框角点
        cm.put("skip_detection", true); // 是否跳过文字检测步骤直接进行文字识别
        cm.put("dir_assure", false); // 是否关闭自动旋转。true：关闭自动旋转。false：开启自动旋转。
        cm.put("language", "sx"); // 当skip_detection为true时，该字段才生效，做单行手写识别。

        Map<String, Object> pm = new HashMap<>();
        pm.put("image", image); // 图片二进制数据的base64编码/图片url
        pm.put("configure", JSON.toJSONString(cm));

        HttpResponse response = null;
        try {
            response = HttpUtil.createPost(url).body(JSON.toJSONString(pm)).headerMap(headers, true).execute();
            if (StrUtil.isNotEmpty(response.body())) {
                JSONObject jsonObject = JSONObject.parseObject(response.body());
                if (!jsonObject.getBoolean("success")) {
                    log.error("OCR识别失败：{}", response.body());
                } else {
                    log.info("OCR识别成功：{}", response.body());
                    return jsonObject.getJSONArray("ret");
                }
            }
        } catch (Exception e) {
            log.info("=== ORC Exception ===", e);
        } finally {
            IoUtil.close(response);
        }
        return null;
    }

}
