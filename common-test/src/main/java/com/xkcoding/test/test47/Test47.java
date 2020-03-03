package com.xkcoding.test.test47;

import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;

/**
 * <p>
 * 测试 emoji
 * 文档：https://github.com/vdurmont/emoji-java/blob/master/EMOJIS.md
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020/3/3 18:54
 */
public class Test47 {
    public static void main(String[] args) {
        String str = "An :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!";
        String result = EmojiParser.parseToUnicode(str);
        System.out.println(result);

        str = "An 😀awesome 😃string with a few 😉emojis!";
        result = EmojiParser.parseToAliases(str);
        System.out.println(result);

        result = EmojiParser.parseToHtmlDecimal(str);
        System.out.println(result);

        System.out.println(str + " 是否包含 emoji : " + EmojiManager.containsEmoji(str));
    }
}
