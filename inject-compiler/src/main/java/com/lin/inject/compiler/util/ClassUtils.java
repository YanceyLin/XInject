package com.lin.inject.compiler.util;

import java.util.List;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/5/28
 *     desc   : 类处理工具
 *     version: 1.0
 * </pre>
 */
public class ClassUtils {

    /**
     * 检测是否继承了某个特殊类/接口
     *
     * @param tm    类信息相关对象
     * @param type  需要继承的类全名（包名+类名）
     * @param types 获取父类的工具
     */
    public static boolean isSubtype(TypeMirror tm, String type, Types types) {
        boolean isSubType = false;
        while (tm != null) { //循环获取父类信息
            if (type.equals(tm.toString())) { //通过全路径是否相等
                isSubType = true;
                break;
            }
            TypeElement superTypeElem = (TypeElement) types.asElement(tm);
            if (superTypeElem != null) {
                if (superTypeElem.getInterfaces() != null && !superTypeElem.getInterfaces().isEmpty()) {
                    List<? extends TypeMirror> aa = superTypeElem.getInterfaces();
                    for (TypeMirror typeMirror : aa) {
                        if (type.equals(typeMirror.toString())) { //通过全路径是否相等
                            isSubType = true;
                            tm = null;
                            break;
                        }
                    }
                } else {
                    tm = superTypeElem.getSuperclass();
                }
            } else { //如果为空, 说明没了父类, 所以直接退出
                break;
            }
        }
        return isSubType;
    }

    /**
     * 检查所获取的类是否符合默认要求的类
     *
     * @param messager         信息打印器
     * @param classElement     类信息
     * @param xInjectClassName 所需标签名称，用于跟踪打印日志所需(如：XInject.class.getSimpleName())
     */
    protected boolean isDefaultValidClass(Messager messager, TypeElement classElement, String xInjectClassName) {

        //我们要求只能标注在类上面
        if (classElement.getKind() != ElementKind.CLASS) {
            printMessage(messager, classElement, "Only classes can be annotated with @%s", classElement.getSimpleName());
            return false;
        }

        // 我们规定类必须是public
        if (!classElement.getModifiers().contains(Modifier.PUBLIC)) {
            printMessage(messager, classElement, "The class %s is not public.", classElement.getQualifiedName().toString());
            return false;
        }

        // 我们规定类不能是抽象类
        if (classElement.getModifiers().contains(Modifier.ABSTRACT)) {
            printMessage(messager, classElement, "The class %s is abstract. You can't annotate abstract classes with @%",
                    classElement.getQualifiedName().toString(), xInjectClassName);
            return false;
        }
        // 检查是否提供了默认公开构造函数  getEnclosedElements用于获取classElement里面包含的所有元素包括，变量，方法，构造函数等
        for (Element enclosed : classElement.getEnclosedElements()) {
            if (enclosed.getKind() == ElementKind.CONSTRUCTOR) {
                ExecutableElement constructorElement = (ExecutableElement) enclosed;
                if (constructorElement.getParameters().size() == 0 && constructorElement.getModifiers().contains(Modifier.PUBLIC)) {
                    // 找到了默认构造函数
                    return true;
                }
            }
        }
        // 没有找到默认构造函数
        printMessage(messager, classElement, "The class %s must provide an public empty default constructor", classElement.getQualifiedName().toString());
        return false;
    }

    /**
     * 输出信息
     */
    protected void printMessage(Messager messager, Element e, String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
    }
}
