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


    public static void putString(Context context, String name, String value) {
        getInstance(context);
        instance.edit().putString(name, value).apply();
    }

    public static String getString(Context context, String name) {
        return getString(context, name, "");
    }

    public static String getString(Context context, String name, String defValue) {
        getInstance(context);
        return instance.getString(name, defValue);
    }

    public static void putInt(Context context, String name, int value) {
        getInstance(context);
        instance.edit().putInt(name, value).apply();
    }

    public static void putInt(Context context, String name, long value) {
        getInstance(context);
        instance.edit().putInt(name, value).apply();
    }

    public static void putRefreshDate(Context context){
        putInt(context,"refresh_date",System.currentTimeMillis());
    }

    public static void getRefreshDate(Context context){

    }

    public static void release() {
        instance = null;
    }
}
