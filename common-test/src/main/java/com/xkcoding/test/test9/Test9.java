package com.xkcoding.test.test9;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 测试 批量修改文件名
 * </p>
 *
 * @package: com.xkcoding.test.test9
 * @description: 测试 批量修改文件名
 * @author: yangkai.shen
 * @date: Created in 2018-12-13 17:22
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test9 {
    public static void main(String[] args) {
        String root = "/Users/yangkai.shen/Movies/微服务项目课件";
        String prefix = "学成在线-";

        System.out.println("重命名开始");
        File[] ls = FileUtil.ls(root);
        List<File> fileList = Arrays.stream(ls)
                .filter(FileUtil::isDirectory)
                .sorted()
                .collect(Collectors.toList());
        for (File file : fileList) {
            String dirName = file.getName();
            String fileName = StrUtil.subAfter(dirName, prefix, false);
            String absolutePath = FileUtil.getAbsolutePath(file);
            File[] innerFileArray = FileUtil.ls(absolutePath);
            if (innerFileArray.length == 1) {
                FileUtil.rename(innerFileArray[0], dirName, true, false);
            } else if (innerFileArray.length > 1) {
                for (int i = 0; i < innerFileArray.length; i++) {
                    FileUtil.rename(innerFileArray[i], fileName + "-" + i, true, false);
                }
            }
        }

        System.out.println("重命名结束");

    }
}
