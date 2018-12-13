package com.xkcoding.test.test8;

import cn.hutool.json.JSONUtil;

/**
 * <p>
 * 测试获取硬件信息
 * </p>
 *
 * @package: com.xkcoding.test.test8
 * @description: 测试获取硬件信息
 * @author: yangkai.shen
 * @date: Created in 2018-12-13 16:54
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test8 {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.copyTo();
        System.out.println("server = " + JSONUtil.toJsonStr(server));
    }
}
