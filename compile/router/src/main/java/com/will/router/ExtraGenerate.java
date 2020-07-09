package com.will.router;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Desc: intent 参数自动注入 生成辅助类
 * <p>
 * @Author: will
 */
public class ExtraGenerate {

    public static String generateExtra(Types typeUtils, Elements elementUtils, TypeElement typeElement, Map.Entry<TypeElement, List<Element>> entry){
        StringBuilder builder = new StringBuilder();
        builder.append("// Generated code from Flash. Do not modify!\n");
        builder.append("package ").append(WRouterGenerate.packageName).append(";\n\n");
        builder.append("import java.lang.String;\n");
        builder.append("import java.util.HashMap;\n");
        builder.append("import java.util.Map;\n");
        builder.append("import com.will.router.ExtraAutoWriter;\n");
        builder.append("public class ").append(getGenerateClassName(typeElement.getSimpleName().toString())).append(" implements ExtraAutoWriter").append("{\n");
        builder.append(" public void write(Object object) {\n");
        String refrence =  entry.getKey().asType().toString();
        builder.append("    ").append(refrence).append(" ").append("t = (").append(refrence).append(")").append("object;\n");
        generateBody(typeUtils, elementUtils, typeElement, builder, entry.getValue());
        builder.append(" }\n");
        builder.append("}\n");

        return builder.toString();
    }

    static String getGenerateClassName(String path){
        return "Extra$$WRouter$$" + path;
    }

    public static String getFullClassName(String simpleClassName){
        return WRouterGenerate.packageName + "." + getGenerateClassName(simpleClassName);
    }

    private static void generateBody(Types typeUtils, Elements elementUtils, TypeElement typeElement, StringBuilder builder, List<Element> elements){
        if (elements == null){
            return;
        }

        //是否Java
        boolean isJava = isJavaFile(elementUtils, typeElement);

        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            //遍历注解节点 生成函数体
            TypeMirror typeMirror = element.asType();//获取注解节点的类型
            //获取TypeKind 枚举类型的序列号
            int type = typeMirror.getKind().ordinal();
            //获取属性名
            String fieldName = element.getSimpleName().toString();
            //获取注解的值
            String extraName = element.getAnnotation(WRouterExtra.class).value();
            extraName = extraName == null || "".equals(extraName) ? fieldName : extraName;

            //默认值
            String defaultValue = "t." + (isJava ? fieldName : "get" + captureName(fieldName) +"()");


            StringBuilder value = new StringBuilder().append("t.getIntent().");
            if (type == TypeKind.BOOLEAN.ordinal()) {
                value.append("getBooleanExtra(\"").append(extraName).append("\", ").append(defaultValue).append(")");
            } else if (type == TypeKind.BYTE.ordinal()) {
                value.append("getByteExtra(\"").append(extraName).append("\", ").append(defaultValue).append(")");
            } else if (type == TypeKind.SHORT.ordinal()) {
                value.append("getShortExtra(\"").append(extraName).append("\", ").append(defaultValue).append(")");
            } else if (type == TypeKind.INT.ordinal()) {
                value.append("getIntExtra(\"").append(extraName).append("\", ").append(defaultValue).append(")");
            } else if (type == TypeKind.LONG.ordinal()) {
                value.append("getLongExtra(\"").append(extraName).append("\", ").append(defaultValue).append(")");
            } else if (type == TypeKind.CHAR.ordinal()) {
                value.append("getCharExtra(\"").append(extraName).append("\", ").append(defaultValue).append(")");
            } else if (type == TypeKind.FLOAT.ordinal()) {
                value.append("getFloatExtra(\"").append(extraName).append("\", ").append(defaultValue).append(")");
            } else if (type == TypeKind.DOUBLE.ordinal()) {
                value.append("getDoubleExtra(\"").append(extraName).append("\", ").append(defaultValue).append(")");
            } else if (type == TypeKind.ARRAY.ordinal()) {
                //数组
                switch (typeMirror.toString()) {
                    case BOOLEANARRAY:
                        value.append("getBooleanArrayExtra(\"").append(extraName).append("\"").append(")");
                        break;
                    case INTARRAY:
                        value.append("getIntArrayExtra(\"").append(extraName).append("\"").append(")");
                        break;
                    case SHORTARRAY:
                        value.append("getShortArrayExtra(\"").append(extraName).append("\"").append(")");
                        break;
                    case FLOATARRAY:
                        value.append("getFloatArrayExtra(\"").append(extraName).append("\"").append(")");
                        break;
                    case DOUBLEARRAY:
                        value.append("getDoubleArrayExtra(\"").append(extraName).append("\"").append(")");
                        break;
                    case BYTEARRAY:
                        value.append("getByteArrayExtra(\"").append(extraName).append("\"").append(")");
                        break;
                    case CHARARRAY:
                        value.append("getCharArrayExtra(\"").append(extraName).append("\"").append(")");
                        break;
                    case LONGARRAY:
                        value.append("getLongArrayExtra(\"").append(extraName).append("\"").append(")");
                        break;
                    case STRINGARRAY:
                        value.append("getStringArrayExtra(\"").append(extraName).append("\"").append(")");
                        break;
                    default:
                        ArrayType arrayType = (ArrayType) typeMirror;
                        if (typeUtils.isSubtype(arrayType.getComponentType(), elementUtils.getTypeElement(PARCELABLE).asType())){
                            value = new StringBuilder().append("(").append(typeMirror.toString()).append(")t.getIntent().").append("getParcelableArrayExtra(\"").append(extraName).append("\"").append(")");
                        }else {
                            throw new RuntimeException("Not Support Extra Type : " + typeMirror + " " + element);
                        }
                }

            }else {
                List<? extends TypeMirror> clazzList = typeUtils.directSupertypes(typeMirror);

                if (clazzList != null && clazzList.size() > 0){
                    String firstClass = typeMirror.toString();
                    System.out.println(firstClass);
                    String clazz = clazzList.get(clazzList.size() - 1).toString();
                    if (clazzList.get(0).toString().startsWith(LIST)){//list
                         value = new StringBuilder().append("(").append(typeMirror.toString()).append(")t.getIntent().");
                         if (firstClass.equals(STRING_ARRAYLIST)){
                             value.append("getStringArrayListExtra(\"").append(extraName).append("\"").append(")");
                         }else if(firstClass.equals(INT_ARRAYLIST)){
                             value.append("getIntegerArrayListExtra(\"").append(extraName).append("\"").append(")");
                         }else if(typeUtils.isSubtype(((DeclaredType)typeMirror).getTypeArguments().get(0), elementUtils.getTypeElement(PARCELABLE).asType())){

                             value = new StringBuilder("t.getIntent().<").append(((DeclaredType) typeMirror).getTypeArguments().get(0).toString()).append(">").append("getParcelableArrayListExtra(\"").append(extraName).append("\"").append(")");
                         }else {
                             throw new RuntimeException("Not Support Extra Type : " + typeMirror + " " +
                                     element);
                         }
                    }else {
                        if (typeMirror.toString().equals(STRING)){
                            value.append("getStringExtra(\"").append(extraName).append("\"").append(")");
                        }else if (PARCELABLE.equals(clazz)){
                            value.append("getParcelableExtra(\"").append(extraName).append("\"").append(")");
                        }else if (SERIALLIZABLE.equals(clazz)){
                            value = new StringBuilder().append("(").append(typeMirror.toString()).append(")t.getIntent().").append("getSerializableExtra(\"").append(extraName).append("\"").append(")");
                        }else {
                            throw new RuntimeException("Not Support Extra Type : " + typeMirror + " " +
                                    element);
                        }
                    }
                }else {
                    throw new RuntimeException("Not Support Extra Type : " + typeMirror + " " +
                            element);
                }

            }
            buiderValue(isJava, builder, value, fieldName);
        }

    }

    private static void buiderValue(boolean isJava, StringBuilder builder, StringBuilder value, String fieldName){
        if (isJava){
            builder.append("t.").append(fieldName).append(" = ").append(value).append(";\n");
        }else {
            builder.append("t.set").append(captureName(fieldName)).append("(").append(value).append(");\n");
        }
    }


    /**
     * 将字符串的首字母转大写
     * @param str 需要转换的字符串
     * @return
     */
    private static String captureName(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


    private static boolean isJavaFile(Elements elements, TypeElement element){
        TypeMirror typeMirror =  elements.getTypeElement("kotlin.Metadata").asType();
        List<? extends AnnotationMirror> annotationMirrors =  element.getAnnotationMirrors();
        if (annotationMirrors != null){
            for (int i = 0; i < annotationMirrors.size(); i++) {
                if (annotationMirrors.get(i).getAnnotationType().toString().contains(typeMirror.toString())){
                    return false;
                }
            }
            return true;
        }else {
            return true;
        }
    }


    private static final String BOOLEANARRAY = "boolean[]";
    private static final String INTARRAY = "int[]";
    private static final String SHORTARRAY = "short[]";
    private static final String FLOATARRAY = "float[]";
    private static final String DOUBLEARRAY = "double[]";
    private static final String BYTEARRAY = "byte[]";
    private static final String CHARARRAY = "char[]";
    private static final String LONGARRAY = "long[]";
    private static final String STRINGARRAY = "java.lang.String[]";
    private static final String PARCELABLE = "android.os.Parcelable";
    private static final String SERIALLIZABLE = "java.io.Serializable";
    private static final String LIST = "java.util.AbstractList";
    private static final String STRING_ARRAYLIST = "java.util.ArrayList<java.lang.String>";
    private static final String INT_ARRAYLIST = "java.util.ArrayList<java.lang.Integer>";
    private static final String STRING = "java.lang.String";
}
