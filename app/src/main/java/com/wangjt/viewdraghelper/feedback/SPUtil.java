package com.wangjt.viewdraghelper.feedback;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wangjt on 2017/8/21.
 * sp util
 */

public class SPUtil {
    public static void put(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("jmc_test", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    public static String get(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("jmc_test", Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }
}
