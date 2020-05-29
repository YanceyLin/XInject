package com.lin.inject.compiler;

import com.google.auto.service.AutoService;
import com.lin.inject.annotation.XInject;
import com.lin.inject.compiler.collect.XInjectCollector;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/1/28
 *     desc   : 自定义编译处理类
 *     version: 1.0
 * </pre>
 */
@AutoService(Processor.class)
public class CoreProcess extends BaseProcessor {

    /**
     * 用于指定自定义注解处理器(Annotation Processor)是注册给哪些注解的(Annotation),
     * 注解(Annotation)指定必须是完整的包名+类名
     * <p>
     * 设置只处理Factory的注解
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(XInject.class.getCanonicalName());
        return annotations;
    }

    /**
     * Annotation Processor扫描出的结果会存储进roundEnvironment中，可以在这里获取到注解内容，编写你的操作逻辑。
     * 注意:process()函数中不能直接进行异常抛出,否则程序会异常崩溃
     * <p>
     * 解析注解
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //信息收集器
        HashMap<String, TypeElement> models = parseValue(roundEnvironment, XInject.class);
        //转化成json写入文件(放入asset中)
        collectClassMessage(models);
        return false;
    }

    private void collectClassMessage(HashMap<String, TypeElement> models) {
        if (models != null && models.size() > 0) {
            //收集信息
            XInjectCollector builder = new XInjectCollector(processingEnv);
            for (Map.Entry<String, TypeElement> entry : models.entrySet()) {
                builder.collectTypeElement(entry.getValue());
            }
            //进行记录
            builder.writeFile(mEnvironment.getFiler());
        }
    }

}



















