package data.rx.Sources;

import data.api.ApiEndpointInterface;
import data.api.responsepojo.LoginResponse;
import data.api.RetrofitProvider;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utility.pojo.User;

public class LoginRxSource {
    Retrofit retrofit = RetrofitProvider.getInstance();
    ApiEndpointInterface apiEndpoint = retrofit.create(ApiEndpointInterface.class);

    public Observable<LoginResponse> loginObservable(User user){
        return apiEndpoint.loginUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
