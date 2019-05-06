package com.xkcoding.test.test25;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * <p>
 * 测试图片生成字符画
 * </p>
 *
 * @package: com.xkcoding.test.test25
 * @description: 测试图片生成字符画
 * @author: yangkai.shen
 * @date: Created in 2019-05-05 16:12
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test25 {
    public static void main(String[] args) {
        String imagePath = "/Users/yangkai.shen/Pictures/头像/avatar.jpg";
        String filePath = "/Users/yangkai.shen/Desktop/avatar.txt";
        String scalePath = "/Users/yangkai.shen/Desktop/avatar_scale.jpg";
        FileUtil.del(filePath);
        transfer(imagePath, filePath, scalePath);
    }

    private static void transfer(String imagePath, String filePath, String scalePath) {

        ImgUtil.scale(FileUtil.file(imagePath), FileUtil.touch(scalePath), 0.5f);

        BufferedImage image = ImgUtil.read(scalePath);
        File file = FileUtil.touch(filePath);

        final String base = "#8XOHLTI)i=+;:,. ";

        for (int y = 0; y < image.getHeight(); y += 2) {
            for (int x = 0; x < image.getWidth(); x++) {
                final int pixel = image.getRGB(x, y);
                final int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
                final float gray = 0.299f * r + 0.578f * g + 0.114f * b;
                final int index = Math.round(gray * (base.length() + 1) / 255);
                String s = index >= base.length() ? " " : String.valueOf(base.charAt(index));
                FileUtil.appendUtf8String(s, file);
                System.out.print(s);
            }
            FileUtil.appendUtf8String("\n", file);
            System.out.println();
        }

        FileUtil.del(scalePath);
    }
}
