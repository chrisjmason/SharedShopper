package data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import data.api.Interceptors.HeaderInterceptor;
import data.api.Interceptors.LoggingInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;


public class RetrofitProvider {
    private static Retrofit instance = null;
    private static String endpoint = "http://www.tot-up.co.uk/sharedshopper/src/ssindex.php/";
    private static RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

    private RetrofitProvider(){}

    public static Retrofit getInstance(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new LoggingInterceptor())
                .build();

        if(instance==null){
            instance = new Retrofit.Builder()
                    .baseUrl(endpoint)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(rxAdapter)
                    .client(client)
                    .build();
        }
        return instance;
    }
}
