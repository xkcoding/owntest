package com.xkcoding.test.test42;

import cn.hutool.core.io.FileUtil;

import java.io.File;

/**
 * <p>
 * 测试 hutool 的 FileUtil
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/12/29 11:17
 */
public class Test42 {
    public static void main(String[] args) {
        String dir = "/Users/yangkai.shen/Desktop";
        System.out.println(FileUtil.listFileNames(dir));
        for (File file : FileUtil.ls(dir)) {
            System.err.println(file.getAbsolutePath());
            if (FileUtil.isDirectory(file)) {
                for (File subFile : FileUtil.ls(file.getAbsolutePath())) {
                    System.err.println("\t" + subFile.getAbsolutePath());
                }
            }
        }
    }
}
