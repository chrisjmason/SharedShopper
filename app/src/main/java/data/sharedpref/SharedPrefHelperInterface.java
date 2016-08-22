package data.sharedpref;

public interface SharedPrefHelperInterface {
    void storeUserInfo(String apikey, String dataid);
    void storeDataid(String dataid);
    String getApikey();
    String getDataid();
    void clearData();
    boolean checkSharedPref();
}
