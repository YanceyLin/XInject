package com.lin.inject.core.util;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lin.inject.annotation.XInject;
import com.lin.inject.annotation.common.XInjectConstant;
import com.lin.inject.annotation.model.XInjectMessage;
import com.lin.inject.core.util.AssetsUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/1/28
 *     desc   : 注解功能初始化解析工具
 *     version: 1.0
 * </pre>
 */
public class XInjectUtils {

    private static Map<String, List<XInjectMessage>> classInfoCacheMap = new HashMap<>();

    /**
     * 读取文件到缓存中
     *
     * @param application 应用上下文
     */
    public static void readFile2Cache(Application application) {
        try {
            String dirName = XInjectConstant.LIN_INJECT_ASSET_DIR_NAME;
            String[] files = application.getAssets().list(dirName);
            if (files != null) {
                Gson gson = new Gson();
                for (String fileName : files) {
                    putCache(application, gson, dirName + "/" + fileName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件信息解析并放入内存中
     */
    private static void putCache(Application application, Gson gson, String filePath) {
        String json = AssetsUtils.readAssets2String(filePath, application);
        Map<String, List<XInjectMessage>> map = gson.fromJson(json, new TypeToken<Map<String, List<XInjectMessage>>>() {
        }.getType());
        if (map != null) {
            for (String key : map.keySet()) {
                List<XInjectMessage> cacheClassInfoList = classInfoCacheMap.get(key);
                cacheClassInfoList = cacheClassInfoList == null ? new ArrayList<>() : cacheClassInfoList;
                cacheClassInfoList.addAll(map.get(key));
                classInfoCacheMap.put(key, cacheClassInfoList);
            }
        }
    }

    public static Map<String, List<XInjectMessage>> getClassInfoCacheMap() {
        return classInfoCacheMap;
    }
}
