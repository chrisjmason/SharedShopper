package data.repos.item;


import java.util.List;

import data.rx.Interactor;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utility.BasePresenterInterface;
import utility.pojo.Item;

public class ItemRepository implements ItemRepositoryInterface {
    BasePresenterInterface presenter;
    Interactor interactor;

    public ItemRepository(BasePresenterInterface presenter){
        this.presenter = presenter;
        interactor = new Interactor();
    }

    @Override
    public void getData() {
        Observable<List<Item>> getDataObservable = interactor.getListFinalObservable();

        getDataObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Item>>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        presenter.showToast("Failure :(");
                    }

                    @Override
                    public void onNext(List<Item> items) {
                        presenter.showItemsView(items);
                    }
                });
    }

    public void getLocalData(){
        Observable<List<Item>> getLocalDataObservable = interactor.getListLocalFinalObservable();

        getLocalDataObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Item>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Item> list) {
                        presenter.showItemsView(list);
                    }
                });
    }

    @Override
    public void addItem(String title, String desc, int colour) {
        Observable<List<Item>> getAddObservable = interactor.addItemFinalObservable(title, desc, colour);

        getAddObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Item>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        presenter.showToast("Items failed to add :(");
                    }

                    @Override
                    public void onNext(List<Item> items) {
                        presenter.showItemsView(items);
                        presenter.showToast("Item Added!");
                    }
                });
    }

    @Override
    public void deleteItem(int position) {
        Observable<List<Item>> getDeleteObservable = interactor.deleteItemFinalObservable(position);

        getDeleteObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Item>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        presenter.showToast("Deletion failed");
                    }

                    @Override
                    public void onNext(List<Item> items) {
                        presenter.showItemsView(items);
                        presenter.showToast("Item deleted");
                    }
                });
    }

    @Override
    public void getItem(int position) {

        Observable<Item> getItemObservable = interactor.getItemFinalObservable(position);
        getItemObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Item>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Item item) {
                        presenter.showItemView(item);
                    }
                });
    }
}
