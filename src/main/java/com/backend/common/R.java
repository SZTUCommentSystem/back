package com.backend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果，服务端响应的数据最终都会封装成此对象
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {

    //编码：1成功，0和其它数字为失败
    private Integer code;

    //信息
    private String message;

    //数据
    private T data;

//    private Map map = new HashMap(); //动态数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.message = "success";
        r.data = object;
        r.code = 200;
        return r;
    }
    public static <T> R<T> success(String key, T object) {
        R<T> r = new R<>();
        r.message = "success";
        r.code = 200;

        // 将数据封装到 Map
        Map<String, T> map = new HashMap<>();
        map.put(key, object);

        r.data = (T) map; // 这里将 map 作为 data 返回
        return r;
    }
    public static <T> R<T> error(String msg) {
        R r = new R();
        r.message = msg;
        r.code = 201;
        return r;
    }

//    public R<T> add(String key, Object value) {
//        this.map.put(key, value);
//        return this;
//    }

}