package com.sppe.shrimppaste.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by @author WangHaoFei on 2018/2/12.
 */

public class SharedPreferencesUtil {

    private static final String SP_FILE_NAME = "shrimp_paste";

    private static SharedPreferences instance = null;

    private static SharedPreferences getInstance(Context context) {
        if (instance == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (instance == null) {
                    instance = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
                }
            }
        }
        return instance;
    }

    private static void putString(Context context, String name, String value) {
        getInstance(context);
        instance.edit().putString(name, value).apply();
    }

    private static String getString(Context context, String name, String defValue) {
        getInstance(context);
        return instance.getString(name, defValue);
    }

    private static void putInt(Context context, String name, int value) {
        getInstance(context);
        instance.edit().putInt(name, value).apply();
    }

    private static void putLong(Context context, String name, long value) {
        getInstance(context);
        instance.edit().putLong(name, value).apply();
    }

    private static long getLong(Context context, String name, long defValue) {
        getInstance(context);
        return instance.getLong(name, defValue);
    }

    public static void putRefreshDate(Context context) {
        putLong(context, "refresh_date", System.currentTimeMillis());
    }

    public static long getRefreshDate(Context context) {
        return getLong(context, "refresh_date", 0);
    }

    public static void release() {
        instance = null;
    }
}
