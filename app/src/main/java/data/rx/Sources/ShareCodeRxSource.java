package data.rx.Sources;

import data.api.ApiEndpointInterface;
import data.api.responsepojo.LoginResponse;
import data.api.RetrofitProvider;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utility.pojo.DataId;

public class ShareCodeRxSource {
    Retrofit retrofit = RetrofitProvider.getInstance();
    ApiEndpointInterface apiEndpoint = retrofit.create(ApiEndpointInterface.class);

    public Observable<LoginResponse> shareCodeObservable(String newCode){
        Observable<LoginResponse> shareObs = apiEndpoint.changeDataid(new DataId(newCode));
        return shareObs.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
