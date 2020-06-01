package com.lin.inject.core.core;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.lin.inject.core.core.executor.XInjectExecutor;
import com.lin.inject.core.util.XInjectUtils;

import java.util.Objects;
/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/1/28
 *     desc   : 为初始化提供触发契机的内容提供者
 *     version: 1.0
 * </pre>
 */
public class XInjectProvider extends ContentProvider {
    public XInjectProvider() {
    }

    /**
     * 在APP开始创建之后，但是在Application的onCreate调用之前调用该方法
     */
    @Override
    public boolean onCreate() {
        Log.d("onCreate", "XInjectProvider");
        Application application = (Application) Objects.requireNonNull(getContext()).getApplicationContext();
        XInjectUtils.readFile2Cache(application);
        XInjectExecutor.init().invokeAllXInjectExecute(application);
        return false;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
