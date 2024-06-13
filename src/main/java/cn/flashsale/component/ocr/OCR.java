package cn.flashsale.component.ocr;

import com.alibaba.fastjson.JSONArray;

/**
 * description...
 *
 * @author linyan-z7
 * @date 2024/6/13
 */
public interface OCR {

    JSONArray recognition(String image);
}


