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
import utility.pojo.Item;
import utility.MyApplication;

public class AddItemRxSource {
    Retrofit retrofit = RetrofitProvider.getInstance();
    ApiEndpointInterface apiEndpoint = retrofit.create(ApiEndpointInterface.class);
    ItemDBHelper db = new ItemDBHelper(MyApplication.getContext());

    public Observable<List<Item>> getAddObservable(final Item itemToAdd){

        return apiEndpoint.addItemApi(itemToAdd)
                .map(new Func1<Response<ResponseBody>, List<Item>>() {
                    @Override
                    public List<Item> call(Response<ResponseBody> responseBodyResponse) {
                        getAddItemDbObservable(itemToAdd,1);
                        return db.getLocalData();
                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<List<Item>>>() {
                    @Override
                    public Observable<List<Item>> call(Throwable throwable) {
                        throwable.printStackTrace();
                        return getAddItemDbObservable(itemToAdd, 0);
                    }
                });

    }

    public Observable<List<Item>> getAddItemDbObservable(Item item, int onlineBool){
        return Observable.just(db.addItem(item, onlineBool));
    }
}
