package com.lin.inject.compiler.collect;

import com.lin.inject.annotation.common.XInjectConstant;

import java.io.IOException;
import java.io.Writer;
import java.util.UUID;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.util.Types;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/5/28
 *     desc   : 信息基础收集器
 *     version: 1.0
 * </pre>
 */
public class XBaseCollector {

    //数据转化器所需
    protected ProcessingEnvironment mProcessingEnvironment;
    //父类寻找器
    protected Types mTypes;

    public XBaseCollector(ProcessingEnvironment processingEnvironment) {
        this.mProcessingEnvironment = processingEnvironment;
        mTypes = processingEnvironment.getTypeUtils();
    }

    /**
     * 将收集到的类信息转化成json写入文件，存入asset中，便于在APP创建初期调用与反射
     *
     * @param filer   注释器用于创建新源、类或辅助文件的 Filer
     * @param content 内容(json格式)
     */
    protected void write2File(Filer filer, String content) {
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + XInjectConstant.LIN_INJECT_JSON_FILE_SUFFIX;
        String filePath = XInjectConstant.LIN_INJECT_ASSET_DIR_PATH + fileName;
        try {
            FileObject fileObject = filer.createResource(StandardLocation.CLASS_OUTPUT, "", filePath);
            Writer writer = fileObject.openWriter();
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
