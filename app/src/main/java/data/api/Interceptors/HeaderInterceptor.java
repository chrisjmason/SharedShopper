package data.api.Interceptors;

import java.io.IOException;

import data.sharedpref.SharedPrefHelper;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import utility.MyApplication;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        SharedPrefHelper sharedPrefHelper = new SharedPrefHelper(MyApplication.getContext());
        String apikey;
        String dataid;

        if(sharedPrefHelper.checkSharedPref()){
            apikey = sharedPrefHelper.getApikey();
            dataid = sharedPrefHelper.getDataid();
        }else{
            apikey = "not available";
            dataid = "not available";
        }

        Request request = chain.request()
                .newBuilder()
                .addHeader("apikey",apikey)
                .addHeader("dataid", dataid)
                .build();

        return chain.proceed(request);
    }
}
