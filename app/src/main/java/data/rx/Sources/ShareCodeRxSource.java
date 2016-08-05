package data.rx.Sources;

import data.api.ApiEndpointInterface;
import data.api.responsepojo.LoginResponse;
import data.api.RetrofitProvider;
import retrofit2.Retrofit;
import rx.Observable;
import utility.pojo.DataId;

public class ShareCodeRxSource {
    Retrofit retrofit = RetrofitProvider.getInstance();
    ApiEndpointInterface apiEndpoint = retrofit.create(ApiEndpointInterface.class);

    public Observable<LoginResponse> shareCodeObservable(String newCode){
        return apiEndpoint.changeDataid(new DataId(newCode));
    }
}
