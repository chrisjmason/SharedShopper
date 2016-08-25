package data.rx.Sources;

import data.db.ItemDBHelper;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utility.pojo.Item;
import utility.MyApplication;

public class GetItemRxSource {
    ItemDBHelper db = new ItemDBHelper(MyApplication.getContext());

    public Observable<Item> getItemObservable(int position){
        return Observable.just(db.getItem(position))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
