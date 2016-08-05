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

    //TODO
    //get db access off main thread
    public Observable<List<Item>> getOnlineAddObservable(){
        final List<Item> uploadItems = db.getToUpload();
        Observable<List<Item>> onlineAddObservable;

        if(uploadItems.size() != 0){
            Observable<Response<ResponseBody>> onlineSyncAdd = apiEndpoint.addItemListApi(uploadItems);

            onlineAddObservable = onlineSyncAdd.subscribeOn(Schedulers.io())
                    .map(new Func1<Response<ResponseBody>, List<Item>>() {
                        @Override
                        public List<Item> call(Response<ResponseBody> responseBodyResponse) {
                            return null;
                        }
                    });
        }else{
            onlineAddObservable = Observable.empty();
        }

        return onlineAddObservable;
    }

    public Observable<List<Item>> getDeletedLocalObservable(){
        final List<Item> deletedLocalItems = db.getDeletedLocal();
        Observable<List<Item>> onlineDeleteObservable;

        if(deletedLocalItems.size() != 0) {
            Observable<Response<ResponseBody>> onlineSyncDelete = apiEndpoint.deleteItemListApi(deletedLocalItems);

            onlineDeleteObservable = onlineSyncDelete.subscribeOn(Schedulers.io())
                    .map(new Func1<Response<ResponseBody>, List<Item>>() {
                        @Override
                        public List<Item> call(Response<ResponseBody> responseBodyResponse) {
                            return null;
                        }
                    });
        }else {
            onlineDeleteObservable = Observable.empty();
        }

        return onlineDeleteObservable;
    }
}
