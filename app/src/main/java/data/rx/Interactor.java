package data.rx;

import java.util.List;

import data.api.responsepojo.LoginResponse;
import data.rx.Sources.AddItemRxSource;
import data.rx.Sources.DeleteItemRxSource;
import data.rx.Sources.GetItemRxSource;
import data.rx.Sources.GetListRxSource;
import data.rx.Sources.LoginRxSource;
import data.rx.Sources.RegisterRxSource;
import data.rx.Sources.ShareCodeRxSource;
import data.rx.Sources.SyncDataRxSource;
import data.sharedpref.SharedPrefHelper;
import rx.Observable;
import rx.functions.Func1;
import utility.util.CodeUtil;
import utility.util.DateUtil;
import utility.pojo.Item;
import utility.MyApplication;
import utility.pojo.User;

public class Interactor {
    SyncDataRxSource syncDataRxSource;

    public Interactor(){
        syncDataRxSource = new SyncDataRxSource();
    }

    public Observable<LoginResponse> getRegisterObservable(User user){
        RegisterRxSource registerRxSource = new RegisterRxSource();
        return registerRxSource.registerObservable(user);
    }

    public Observable<LoginResponse> getLoginObservable(User user){
        LoginRxSource loginRxSource = new LoginRxSource();
        return  loginRxSource.loginObservable(user);
    }

    public Observable<LoginResponse> getShareObservable(String newCode){
        ShareCodeRxSource shareCodeRxSource = new ShareCodeRxSource();

        return shareCodeRxSource.shareCodeObservable(newCode);
    }

    public Observable<List<Item>> getListFinalObservable(){
        final GetListRxSource getListRxSource = new GetListRxSource();

        return syncDataRxSource.getDeletedLocalObservable()
                .concatWith(syncDataRxSource.getOnlineAddObservable())
                .concatWith(getListRxSource.getListObservable())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<Item>>>() {
                    @Override
                    public Observable<? extends List<Item>> call(Throwable throwable) {
                        return getListRxSource.getListDbObservable();
                    }
                });
    }

    public Observable<List<Item>> getListLocalFinalObservable(){
        GetListRxSource getListRxSource = new GetListRxSource();
        return getListRxSource.getListDbObservable();
    }


    public Observable<List<Item>> addItemFinalObservable(String title, String desc, int colour){
        SharedPrefHelper sharedPrefHelper = new SharedPrefHelper(MyApplication.getContext());
        final AddItemRxSource addItemRxSource = new AddItemRxSource();
        final Item itemToAdd = new Item(title,desc,colour, DateUtil.getDate(),DateUtil.getTimestamp(), CodeUtil.getCode(), sharedPrefHelper.getDataid());

        return addItemRxSource.getAddObservable(itemToAdd)
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<Item>>>() {
                    @Override
                    public Observable<? extends List<Item>> call(Throwable throwable) {
                        throwable.printStackTrace();
                        return addItemRxSource.getAddItemDbObservable(itemToAdd,0);
                    }
                });
    }


    public Observable<List<Item>> deleteItemFinalObservable(final int itemId){
        final DeleteItemRxSource deleteItemRxSource = new DeleteItemRxSource();

        return deleteItemRxSource.getDeleteObservable(itemId)
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<Item>>>() {
                    @Override
                    public Observable<? extends List<Item>> call(Throwable throwable) {
                        throwable.printStackTrace();
                        return deleteItemRxSource.deleteItemDbObservable(itemId);
                    }
                });
    }


    public Observable<Item> getItemFinalObservable(int position){
        final GetItemRxSource getItemRxSource = new GetItemRxSource();
        return getItemRxSource.getItemObservable(position);
    }
}
