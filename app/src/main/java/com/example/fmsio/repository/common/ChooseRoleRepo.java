package com.example.fmsio.repository.common;

import android.content.Context;
import android.content.SharedPreferences;

public class ChooseRoleRepo {
    public static int getRole(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("role", -1);
    }

    public static void setRole(Context context, int role){
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("role", role);
        editor.apply();
    }
}
