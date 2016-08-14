package data.rx.Sources;


import java.util.List;

import data.api.ApiEndpointInterface;
import data.api.RetrofitProvider;
import data.db.ItemDBHelper;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import utility.pojo.Item;
import utility.MyApplication;

public class SyncDataRxSource {
    Retrofit retrofit = RetrofitProvider.getInstance();
    ApiEndpointInterface apiEndpoint = retrofit.create(ApiEndpointInterface.class);
    ItemDBHelper db = new ItemDBHelper(MyApplication.getContext());

    public Observable<List<Item>> getOnlineAddObservable(){
        return Observable.just(db.getToUpload())
                .subscribeOn(Schedulers.io())
                .concatMap(new Func1<List<Item>, Observable<? extends List<Item>>>() {
                    @Override
                    public Observable<? extends List<Item>> call(List<Item> items) {
                        if(items.size()!=0) {
                           return apiEndpoint.addItemListApi(items)
                                    .map(new Func1<Response<ResponseBody>, List<Item>>() {
                                        @Override
                                        public List<Item> call(Response<ResponseBody> responseBodyResponse) {
                                            return null;
                                        }
                                    });
                        }else{
                            return Observable.empty();
                        }
                    }
                });
    }

    public Observable<List<Item>> getDeletedLocalObservable(){
        return Observable.just(db.getDeletedLocal())
                .subscribeOn(Schedulers.io())
                .concatMap(new Func1<List<Item>, Observable<? extends List<Item>>>() {
                    @Override
                    public Observable<? extends List<Item>> call(List<Item> items) {
                        if(items.size()!=0) {
                            return apiEndpoint.deleteItemListApi(items)
                                    .map(new Func1<Response<ResponseBody>, List<Item>>() {
                                        @Override
                                        public List<Item> call(Response<ResponseBody> responseBodyResponse) {
                                            return null;
                                        }
                                    });
                        }else{
                            return Observable.empty();
                        }
                    }
                });
    }
}
