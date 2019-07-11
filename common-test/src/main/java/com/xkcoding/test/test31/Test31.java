package com.xkcoding.test.test31;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.util.Optional;

/**
 * <p>
 * 测试 java parser 获取注释
 * </p>
 *
 * @package: com.xkcoding.test.test31
 * @description: 测试 java parser 获取注释
 * @author: yangkai.shen
 * @date: Created in 2019-07-11 09:18
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test31 {
    public static void main(String[] args) {
        CompilationUnit compilationUnit = StaticJavaParser.parse("/** 测试注释 */ class A { }");
        Optional<ClassOrInterfaceDeclaration> classA = compilationUnit.getClassByName("A");
        classA.ifPresent(classOrInterfaceDeclaration -> classOrInterfaceDeclaration.getJavadocComment().ifPresent(System.out::println));
    }
}
