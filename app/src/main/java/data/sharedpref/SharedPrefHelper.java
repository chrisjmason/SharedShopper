package data.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper implements SharedPrefHelperInterface {
    Context context;
    SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "userdata";
    private static final String API_KEY = "apikey";
    private static final String DATA_ID = "dataid";

    public SharedPrefHelper(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void storeUserInfo(String apikey, String dataid) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(API_KEY, apikey);
        editor.putString(DATA_ID, dataid);
        editor.apply();
    }

    @Override
    public void storeDataid(String dataid){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DATA_ID, dataid);
        editor.apply();
    }

    @Override
    public String getApikey() {
        return sharedPreferences.getString(API_KEY, null);
    }

    @Override
    public String getDataid() {
        return sharedPreferences.getString(DATA_ID,null);
    }

    @Override
    public void clearData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public boolean checkSharedPref(){
        String apikey = getApikey();
        String dataid = getDataid();

        return apikey!=null && dataid!=null;
    }
}
