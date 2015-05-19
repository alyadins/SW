package ru.thinone.schoolwallet.model.DataProvider;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by alexandrlyadinskii on 19.05.15.
 * All rights reserved©
 */
public class TimeSettings {

    private static final String PREF_NAME = "school_data_ttl";

    private static final long SECOND = 1000;
    private static final long MINUTE = 60 * SECOND;
    private static final long HOUR = 60 * MINUTE;
    private static final long DAY = 24 * HOUR;

    public static final String SERVICES = "services_ttl";

    public static final long SERVICES_TTL = 5 * MINUTE;

    public static void  updateTime(Context context, String type) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        long time = System.currentTimeMillis();
        editor.putLong(type, time);
        editor.apply();
    }

    public static void resetTime(Context context, String type) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putLong(type, 0);
        editor.apply();
    }

    public static long getTime(Context context, String type) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getLong(type, 0);
    }

    public static boolean isActual(Context context, String type, long ttl) {
        if (System.currentTimeMillis() - getTime(context, type) < ttl) {
            return true;
        }
        return false;
    }

    public static boolean isActual(long updateTime, long ttl) {
        if (System.currentTimeMillis() - updateTime < ttl) {
            return true;
        }

        return false;
    }
}
