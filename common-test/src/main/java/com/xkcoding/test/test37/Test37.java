package com.xkcoding.test.test37;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.RuntimeUtil;

/**
 * <p>
 * 测试 hutool 执行shell脚本
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-08-22 14:55
 */
public class Test37 {
    public static void main(String[] args) {

        ClassPathResource resource = new ClassPathResource("test37.sh");
        String s = RuntimeUtil.execForStr("sh",resource.getAbsolutePath(),"参数1|参数2");
        System.out.println("s = " + s);
    }
}
