package com.lin.inject.compiler;

import java.lang.annotation.Annotation;
import java.util.HashMap;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

public abstract class BaseProcessor extends AbstractProcessor {

    /**
     * 用于创建文件
     */
    protected ProcessingEnvironment mEnvironment;
    /**
     * 用于打印信息
     */
    protected Messager mMessager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mEnvironment = processingEnvironment;
        mMessager = processingEnvironment.getMessager();
    }


    /**
     * 输出信息
     */
    protected void printMessage(Element e, String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
    }


    protected HashMap<String, TypeElement> parseValue(RoundEnvironment roundEnvironment, Class<? extends Annotation> targetClass) {
        HashMap<String, TypeElement> data = new HashMap<>();
        //遍历所有元素带有XInject注解的元素(可能是类、接口、变量、方法)
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(targetClass)) {
            //我们要求只能标注在类上面
            if (annotatedElement.getKind() != ElementKind.CLASS) {
                printMessage(annotatedElement, "Only classes can be annotated with @%s", annotatedElement.getSimpleName());
                return data;
            }

            //因为我们已经知道它是ElementKind.CLASS类型，所以可以直接强制转换，转换成TypeElement类型，TypeElement就是值接口或者类
            TypeElement typeElement = (TypeElement) annotatedElement;
            String tag = typeElement.getQualifiedName().toString();
            data.put(tag,typeElement);
        }
        return data;
    }


    /**
     * 这个函数一般不用动，返回SourceVersion.latestSupported();就好
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
