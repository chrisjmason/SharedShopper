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

public class DeleteItemRxSource {

    Retrofit retrofit = RetrofitProvider.getInstance();
    ApiEndpointInterface apiEndpoint = retrofit.create(ApiEndpointInterface.class);
    ItemDBHelper db = new ItemDBHelper(MyApplication.getContext());

    public Observable<List<Item>> getDeleteObservable(final int itemId){
        Item item = db.getItem(itemId);

        return apiEndpoint.deleteItemApi(item.getCode())
                .map(new Func1<Response<ResponseBody>, List<Item>>() {
                    @Override
                    public List<Item> call(Response<ResponseBody> responseBody) {
                        db.deleteItem(itemId);
                        return db.getLocalData();
                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<Item>>>() {
                    @Override
                    public Observable<? extends List<Item>> call(Throwable throwable) {
                        throwable.printStackTrace();
                        return deleteItemDbObservable(itemId);
                    }
                });

    }

    public Observable<List<Item>> deleteItemDbObservable(int itemId){
        Item item = db.getItem(itemId);
        return  Observable.just(db.updateItem(item.getCode(), ItemDBHelper.ITEM_DELETEDLOCALBOOL));
    }
}
