package askdat.pyvela;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefsClass {
    public SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;

    public void appPrefs(Context context){
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }
    public void saveInt(String key, int value){
        editor = sharedPrefs.edit();
        editor.putInt(key,value);
        editor.apply();
    }
    public void saveStr(String key,String value){
        editor = sharedPrefs.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public void saveBool(String key,boolean value){
        editor = sharedPrefs.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
}
