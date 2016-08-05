package data.rx.Sources;

import data.db.ItemDBHelper;
import rx.Observable;
import utility.pojo.Item;
import utility.MyApplication;

public class GetItemRxSource {
    ItemDBHelper db = new ItemDBHelper(MyApplication.getContext());

    public Observable<Item> getItemObservable(int position){
        return Observable.just(db.getItem(position));
    }
}
