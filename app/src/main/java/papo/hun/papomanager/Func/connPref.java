package papo.hun.papomanager.Func;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class connPref {
    private SharedPreferences preferences;

    public connPref(Context mContext) {
        preferences = mContext.getSharedPreferences("UserInfo", MODE_PRIVATE);
    }

    public void prefSave(String name, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, value);
        editor.apply();
    }

    public String prefLoad(String key) {
        return preferences.getString(key, "None");
    }

    public String getLayoutSET(String key) {
        return preferences.getString(key, "menuLIST");
    }

    public void regitDevice(String serial, Set<String> deviceInfo) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(serial, deviceInfo);
        editor.apply();
    }

    public void clear() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}