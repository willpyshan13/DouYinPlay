package com.will.routercompiler;

import com.google.auto.service.AutoService;
import com.will.router.WRouter;
import com.will.router.WRouterExtra;
import com.will.router.WRouterGenerate;
import com.will.router.ExtraGenerate;


import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;


/**
 * Desc:路由注解解释器
 *
 * @Author: will
 */
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ProtocolProcessor extends AbstractProcessor {

    private static final String OBJECT = "java.lang.Object";

    Elements elementUtils;
    Types typeUtils;
    Filer filer;

    /**
     * init()方法会被注解处理工具调用，并输入ProcessingEnviroment参数。
     * ProcessingEnviroment提供很多有用的工具类Elements, Types 和 Filer
     *
     * @param env 提供给 processor 用来访问工具框架的环境
     */
    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        this.elementUtils = env.getElementUtils();
        this.typeUtils = env.getTypeUtils();
        this.filer = env.getFiler();
    }


    /**
     * 这相当于每个处理器的主函数main()，你在这里写你的扫描、评估和处理注解的代码，以及生成Java文件。
     * 输入参数RoundEnviroment，可以让你查询出包含特定注解的被注解元素
     *
     * @param annotations 请求处理的注解类型
     * @param roundEnv    有关当前和以前的信息环境
     * @return 如果返回 true，则这些注解已声明并且不要求后续 Processor 处理它们；
     * 如果返回 false，则这些注解未声明并且可能要求后续 Processor 处理它们
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        this.processorRouterPage(roundEnv);
        return true;
    }

    /**
     * 路由跳转
     * @param roundEnv
     */
    private void processorRouterPage(RoundEnvironment roundEnv){
        Class<? extends Annotation> clazz = WRouter.class;
        Iterator var = roundEnv.getElementsAnnotatedWith(clazz).iterator();
        HashMap<String, HashMap<String, String>> classHashMap = new HashMap<>();
        while (var.hasNext()) {
            Element element = (Element) var.next();
            try {
                TypeElement typeElement = (TypeElement) element;
                Annotation annotation = element.getAnnotation(clazz);
                Method pathMethod = clazz.getDeclaredMethod("path");
                Method groupMethod = clazz.getDeclaredMethod("group");
                String path = (String) pathMethod.invoke(annotation);
                String group = (String) groupMethod.invoke(annotation);
                if (group != null && !"".equals(group)){
                    path = group + (path.startsWith("/") ? path : "/" + path);
                }else if(path != null && !"".equals(path)){
                    group = URI.create("Router://" + path).getHost();
                }
                if (group != null && !"".equals(group)){
                    HashMap temp = classHashMap.get(group);
                    if (temp == null){
                        temp = new HashMap<String, String>();
                    }
                    temp.put(path, typeElement.getQualifiedName().toString());
                    classHashMap.put(group, temp);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        Iterator varExtra = roundEnv.getElementsAnnotatedWith(WRouterExtra.class).iterator();
        Map<TypeElement, List<Element>> extraMap = new HashMap<>();
        while (varExtra.hasNext()) {
            Element element = (Element) varExtra.next();
            try {
                TypeElement typeElement = (TypeElement) element.getEnclosingElement();
                List<Element> temp = extraMap.get(typeElement);
                if (temp == null){
                    temp = new ArrayList<>();
                }
                temp.add(element);
                extraMap.put(typeElement, temp);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if (extraMap.size() > 0){
            for (Map.Entry<TypeElement, List<Element>> entry: extraMap.entrySet()) {
                TypeElement typeElement = entry.getKey();
                String name = typeElement.getSimpleName().toString();
                writerClass(ExtraGenerate.generateExtra(typeUtils, elementUtils, typeElement, entry), ExtraGenerate.getFullClassName(name));
            }
        }
        if (classHashMap.size() > 0){
            for (Map.Entry<String, HashMap<String, String>> entry: classHashMap.entrySet()) {
                writerClass(WRouterGenerate.generateGroup(entry.getKey(), entry.getValue()), WRouterGenerate.getFullGenerateClassName(entry.getKey()));
            }
        }
    }

    private void writerClass(String text, String classFullName) {
        try {
            if (text != null && !text.equals("") && classFullName != null && !classFullName.equals("")){
                JavaFileObject fileObject = this.filer.createSourceFile(classFullName, (Element[]) null);
                Writer writer = fileObject.openWriter();
                writer.write(text);
                writer.flush();
                writer.close();
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    /**
     * 这里必须指定，这个注解处理器是注册给哪个注解的。注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称
     *
     * @return 注解器所支持的注解类型集合，如果没有这样的类型，则返回一个空集合
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet();
        //路由跳转
        types.add(WRouter.class.getCanonicalName());
        types.add(WRouterExtra.class.getCanonicalName());
        return types;
    }

    /**
     * 指定使用的Java版本，通常这里返回SourceVersion.latestSupported()，默认返回SourceVersion.RELEASE_6
     *
     * @return 使用的Java版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }
}
