package com.nwafu.PISMDB.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 用于最终callback结果集的封装
 * 使用这个的原因是解决跨域问题
 * 在 jQuery 中，可以通过使用JSONP 形式的回调函数来加载其他网域的JSON数据
 *
 */


@Data
public class CallbackResult<T> {
    private String callback;
    private T data;

    public String changToJsonp(){
        return callback + "(" + JSONObject.toJSONString(data) + ");";
    }

}
