package com.will.router;

import java.util.Map;

/**
 * Desc:路由参数生成器
 * <p>
 * @Author: will
 */
public class WRouterGenerate {

    public static String packageName = "com.will.router.data.activity";


    public static String generateGroup(String group, Map<String, String> classMap) {
        StringBuilder builder = new StringBuilder();
        generateHeader(builder, group);
        for (Map.Entry<String, String> entry: classMap.entrySet()) {
            String path = entry.getKey();
            if (path  != null && !"".equals(path)){
                generateCase(builder, path, entry.getValue());
            }
        }
        generateFooter(builder);
        return builder.toString();
    }

    private static void generateHeader(StringBuilder builder, String path){
        builder.append("// Generated code from Flash. Do not modify!\n");
        builder.append("package ").append(packageName).append(";\n\n");
        builder.append("import java.lang.String;\n");
        builder.append("import java.util.HashMap;\n");
        builder.append("import java.util.Map;\n");
        builder.append("import com.will.router.ClassFactory;\n");
        builder.append("public class ").append(getGenerateClassName(path)).append(" implements ClassFactory").append("{\n");
        builder.append(" public Class getTargetClass(String uri){\n");
        builder.append("    switch (uri){\n");
    }

    public static String getFullGenerateClassName(String path){
        return packageName + ".Activity$$WRouter$$" + path;
    }

    static String getGenerateClassName(String path){
        return "Activity$$WRouter$$" + path;
    }

    private static void generateFooter(StringBuilder builder){
        builder.append("    }\n");
        builder.append("    return null;\n");
        builder.append(" }\n");
        builder.append("}\n");
    }

    private static void generateCase(StringBuilder builder, String uri, String path){
        builder.append("       case \"").append(uri).append("\":\n");
        builder.append("           return ").append(path).append(".class;\n");
    }

}
