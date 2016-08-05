package data.rx.Sources;

import java.util.List;

import data.api.ApiEndpointInterface;
import data.api.responsepojo.ItemListResponse;
import data.api.RetrofitProvider;
import data.db.ItemDBHelper;
import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;
import utility.pojo.Item;
import utility.MyApplication;

public class GetListRxSource {

    Retrofit retrofit = RetrofitProvider.getInstance();
    ApiEndpointInterface apiEndpoint = retrofit.create(ApiEndpointInterface.class);
    ItemDBHelper db = new ItemDBHelper(MyApplication.getContext());

    public Observable<List<Item>> getListObservable(){
        return apiEndpoint.getItemList()
                .map(new Func1<ItemListResponse, List<Item>>() {
                    @Override
                    public List<Item> call(ItemListResponse itemListResponse) {
                        List<Item> itemList = itemListResponse.getList();
                        db.updateData(itemList);
                        return itemList;
                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<Item>>>() {
                    @Override
                    public Observable<? extends List<Item>> call(Throwable throwable) {
                        return getListDbObservable();
                    }
                });


    }

    public Observable<List<Item>> getListDbObservable(){
        return Observable.just(db.getLocalData());
    }
}
