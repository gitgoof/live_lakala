
package com.lakala.platform.common;


import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.lakala.core.LibApplicationEx;

/**
 * 此类用于记录和应用生命周期相关的一些参数 统一管理存入sp中的数据
 * 使用的key也要在此类中统一定义
 * @author xyz
 */
public class LklPreferences {

    /**
     * 存入LklPreferences的数据，key都在此类中定义
     */
    public static final String DEMO_KEY = "DEMO_KEY";


    private static LklPreferences instance;

    private SharedPreferences sp;

    private Editor editor;

    private LklPreferences() {
        sp = PreferenceManager.getDefaultSharedPreferences(LibApplicationEx.getInstance());
        editor = sp.edit();
    }

    public static synchronized LklPreferences getInstance() {
        if (instance == null) {
            instance = new LklPreferences();
        }
        return instance;
    }

    public void remove(String key){
    	editor.remove(key);
    	editor.commit();
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void putFloat(String key,float value){
        editor.putFloat(key, value);
        editor.commit();
    }


    public void putLong(String key,long value){
        editor.putLong(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return sp.getString(key, "");
    }

    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public boolean getBoolean(String key,boolean devaultValue) {
        return sp.getBoolean(key, devaultValue);
    }
    
    public int getInt(String key) {
        return sp.getInt(key, 0);
    }

    public float getFloat(String key,float defaultValue){
        return sp.getFloat(key,defaultValue);
    }

    public long getLong(String key,long defaultValue){
        return sp.getLong(key,defaultValue);
    }
}
