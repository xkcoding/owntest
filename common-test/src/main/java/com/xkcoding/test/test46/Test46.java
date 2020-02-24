package com.xkcoding.test.test46;

import cn.hutool.core.convert.Convert;
import com.google.common.collect.Lists;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 测试 Groovy
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020/2/24 13:24
 */
public class Test46 {
    public static void main(String[] args) {
        shellTest();
        test1();
        test2();
        test3();
    }

    /**
     * 规则：字段1 in () 多选，如果字段1 != 数字，则字段2长度<=5
     */
    private static void test3() {
        //language=Groovy
        String script = "x = x.toString().tokenize(\",\")\n" + "list = list.collect{it -> it.toString()}\n" + "def condition = x" + ".every{it -> list.contains(it)}\n" + "\n" + "if (condition" + ") {\n" + "    return x != 1 && y.toS" + "tri" + "ng().l" + "ength() <= 5\n" + "} else {\n" + "    return false\n" + "}";

        Map<String, Object> params = new HashMap<>();
        params.put("x", "111,222");
        params.put("y", 40000);
        params.put("list", Lists.newArrayList(1,111,222));

        Binding binding = new Binding();
        bindingVar(binding, params);

        final Boolean result = eval(binding, script, Boolean.class);
        System.out.println(result);
    }

    /**
     * 规则：字段1 in () 单选，如果字段1=数字，则字段2长度<=5
     */
    private static void test2() {
        //language=Groovy
        String script = "if (list.contains(x)) {\n" + "    return x == 1 && y.toString().length() <= 5\n" + "} e" + "lse {\n" + "    return false\n" + "}";

        Map<String, Object> params = new HashMap<>();
        params.put("x", 1);
        params.put("y", 40000);
        params.put("list", Lists.newArrayList(1,11,22));

        Binding binding = new Binding();
        bindingVar(binding, params);

        final Boolean result = eval(binding, script, Boolean.class);
        System.out.println(result);
    }

    /**
     * 规则：字段1=数字，则字段2不为空且字段2长度<=5
     */
    private static void test1() {
        //language=Groovy
        String script = "if (x == 1){\n" + "    return y && y.toString().length() <= 5" + "\n" + "}else {\n" + "    return false\n" + "}";

        Map<String, Object> params = new HashMap<>();
        params.put("x", 1);
        params.put("y", 400000);

        Binding binding = new Binding();
        bindingVar(binding, params);

        final Boolean result = eval(binding, script, Boolean.class);
        System.out.println(result);
    }

    private static void shellTest() {
        Map<String, Object> params = new HashMap<>();
        params.put("language", "Groovy");
        params.put("x", 10);

        Binding binding = new Binding();
        bindingVar(binding, params);

        //language=Groovy
        String script = "println \"Welcome to $language\"; y = x * 2; z = x * 3; return x ";
        final Integer value = eval(binding, script, Integer.class);

        System.out.println(value + ", " + value.equals(10));
        System.out.println(binding.getVariable("y") + ", " + binding.getVariable("y").equals(20));
        System.out.println(binding.getVariable("z") + ", " + binding.getVariable("z").equals(30));
    }

    private static <T> T eval(Binding binding, String script, Class<T> clazz) {
        GroovyShell shell = new GroovyShell(binding);
        Object value = shell.evaluate(script);
        // clear cache
        shell.getClassLoader().clearCache();

        return Convert.convert(clazz, value);
    }

    private static void bindingVar(Binding binding, Map<String, Object> params) {
        params.forEach(binding::setVariable);
    }
}
