package com.xkcoding.test.test38;

import net.dreamlu.mica.http.HttpRequest;
import net.dreamlu.mica.http.LogLevel;

/**
 * <p>
 * 测试 mica-http 1.1.9
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/30 17:06
 */
public class Test38 {
    public static void main(String[] args) {
        String body = HttpRequest.get("https://github.com").log(LogLevel.BODY).execute().asString().trim();
        // System.out.println("body = " + body);
    }
}
