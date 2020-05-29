package com.lin.inject.compiler.collect;

import com.google.gson.Gson;
import com.lin.inject.annotation.XInject;
import com.lin.inject.annotation.model.XInjectMessage;
import com.lin.inject.compiler.util.ClassUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/1/28
 *     desc   : 将数据信息收集并进行记录
 *     version: 1.0
 * </pre>
 */
public class XInjectCollector extends XBaseCollector{

    private static final String DEFAULT_NAME = "com.lin.inject.core.bridge.XInjectAppCore";

    /**
     * 数据收集容器
     * key:组别名称
     * value(map):类信息记录集合
     */
    private HashMap<String, List<XInjectMessage>> mData;

    public XInjectCollector(ProcessingEnvironment processingEnvironment) {
        super(processingEnvironment);
        mData = new HashMap<>();
    }

    /**
     * 将类信息进行收集
     *
     * @param typeElement 类信息
     */
    public void collectTypeElement(TypeElement typeElement) {
        if (!ClassUtils.isSubtype(typeElement.asType(), DEFAULT_NAME, mTypes)) {
            this.mProcessingEnvironment.getMessager().printMessage(Diagnostic.Kind.ERROR, "------标记为XInject的注解必须继承XInjectAppCore类-----" + typeElement.asType());
            return;
        }

        XInject xInject = typeElement.getAnnotation(XInject.class);
        List<XInjectMessage> annotationMessageList = mData.get(xInject.group());
        if (annotationMessageList == null) {
            annotationMessageList = new ArrayList<>();
            mData.put(xInject.group(), annotationMessageList);
        }
        String className = typeElement.getQualifiedName().toString();
        annotationMessageList.add(new XInjectMessage(className, xInject));
    }

    /**
    * 将收集到的类信息转化成json写入文件，存入asset中，便于在APP创建初期调用与反射
     * @param filer 注释器用于创建新源、类或辅助文件的 Filer
    */
    public void writeFile(Filer filer) {
        write2File(filer,new Gson().toJson(mData));
    }
}
