package com.xkcoding.test.test44;

import org.docx4j.fonts.PhysicalFonts;

/**
 * <p>
 * 测试获取系统字体
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020/2/13 17:07
 */
public class Test44_2 {
    public static void main(String[] args) throws Exception {
        // 加载系统字体
        PhysicalFonts.discoverPhysicalFonts();
        exists("隶书", "LiSu");
        exists("宋体", "SimSun");
        exists("微软雅黑", "Microsoft Yahei");
        exists("黑体", "SimHei");
        exists("楷体", "KaiTi");
        exists("新宋体", "NSimSun");
        exists("华文行楷", "STXingkai");
        exists("华文仿宋", "STFangsong");
        exists("仿宋", "FangSong");
        exists("幼圆", "YouYuan");
        exists("华文宋体", "STSong");
        exists("华文中宋", "STZhongsong");
        exists("等线", "SimSun");
        exists("等线 Light", "SimSun");
        exists("华文琥珀", "STHupo");
        exists("华文隶书", "STLiti");
        exists("华文新魏", "STXinwei");
        exists("华文彩云", "STCaiyun");
        exists("方正姚体", "FZYaoti");
        exists("方正舒体", "FZShuTi");
        exists("华文细黑", "STXihei");
        exists("宋体扩展", "simsun-extB");
        exists("仿宋_GB2312", "FangSong_GB2312");
        exists("新細明體", "SimSun");
        //解决宋体（正文）和宋体（标题）的乱码问题
        // PhysicalFonts.put("PMingLiU",PhysicalFonts.get("SimSun"));
        // PhysicalFonts.put("新細明體", PhysicalFonts.get("SimSun"));
    }

    public static boolean exists(String label, String key) {
        boolean exists = PhysicalFonts.get(key) != null;
        if (exists) {
            System.out.println(label + "存在");
        }

        return exists;
    }
}
