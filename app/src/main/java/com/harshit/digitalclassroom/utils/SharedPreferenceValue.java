package com.harshit.digitalclassroom.utils;


import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferenceValue {

    public static  void updatToken(Context context,String value)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("users",MODE_PRIVATE);
        SharedPreferences.Editor edit=sharedPreferences.edit();
        edit.putString("token",value);
        edit.apply();

    }

    public static String getToken(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("users",MODE_PRIVATE);
        return sharedPreferences.getString("token","");

    }
    public static  void updateDefaultView(Context context,String value)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("users",MODE_PRIVATE);
        SharedPreferences.Editor edit=sharedPreferences.edit();
        edit.putString("defaultView",value);
        edit.apply();

    }

    public static String getDefaultView(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("users",MODE_PRIVATE);
        return sharedPreferences.getString("defaultView","0");

    }


//    public ArrayList<String> allFavImages(){
//        SharedPreferences sharedPreferences=context.getSharedPreferences("users",MODE_PRIVATE);
//        Map<String, ?> allEntries = sharedPreferences.getAll();
//        ArrayList<String> arrFav = new ArrayList<>();
//        Log.d("mapvalues", "At Console");
//
//        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
//            Log.d("map values", entry.getValue().toString());
//            if(entry.getKey().startsWith("fav") && !entry.getValue().equals("-1")){
//                arrFav.add(entry.getValue().toString());
//            }
//        }
//        return arrFav;
//    }

}
