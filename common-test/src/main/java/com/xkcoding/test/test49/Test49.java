package com.xkcoding.test.test49;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;

/**
 * <p>
 * 测试文件迁移
 * </p>
 *
 * @author 一珩（沈扬凯 yk.shen@tuya.com）
 * @date 2021-01-18 15:19
 */
public class Test49 {
    public static void main(String[] args) {
        String src = "/Users/yangkai.shen/Codes/xkcoding/spring-boot-demo";
        String desc = "/Users/yangkai.shen/Codes/xkcoding/wiki/docs/spring-boot-demo";

        File file = FileUtil.file(src);
        File[] allFiles = file.listFiles();
        if (ArrayUtil.isEmpty(allFiles)) {
            System.out.println("no sub file");
            return;
        }
        for (File subFile : allFiles) {
            if (FileUtil.isDirectory(subFile)) {
                String newFileName = subFile.getName() + ".md";
                String readmePath = FileUtil.listFileNames(subFile.getAbsolutePath()).stream().filter("README.md"::equals).findFirst()
                        .map(x -> StrUtil.concat(true, subFile.getAbsolutePath(), StrUtil.SLASH, x)).orElse(StrUtil.EMPTY);
                if (StrUtil.isNotBlank(readmePath)) {
                    FileUtil.copy(readmePath, desc + StrUtil.SLASH + newFileName, false);
                }
            }
        }
    }
}
