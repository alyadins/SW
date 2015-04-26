package ru.thinone.schoolwallet.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class SettingsHelper {

    private static final String PREF_NAME = "school_wallet_settings";

    private static final String USER_ID = "user_id";

    public static void saveUserId(Context context, String id) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_ID, id);
        editor.apply();
    }

    public static String getUserId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(USER_ID, "");
    }
}
