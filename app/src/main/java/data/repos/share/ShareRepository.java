package data.repos.share;

import data.api.responsepojo.LoginResponse;
import data.rx.Interactor;
import data.sharedpref.SharedPrefHelper;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import share.ShareInterface;
import utility.MyApplication;

public class ShareRepository implements ShareRepositoryInterface {
    ShareInterface.Presenter presenter;

    public ShareRepository(ShareInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void changeShareCode(String newCode) {
        Interactor interactor = new Interactor();
        Observable<LoginResponse> shareCodeObservable = interactor.getShareObservable(newCode);

        shareCodeObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        presenter.codeNotChanged("Error, please try again later");
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        if(loginResponse.getError()){
                            presenter.codeNotChanged(loginResponse.getMessage());
                        }else{
                            SharedPrefHelper sharedPrefHelper = new SharedPrefHelper(MyApplication.getContext());
                            sharedPrefHelper.storeDataid(loginResponse.getDataid());
                            presenter.codeChanged();
                        }
                    }
                });
    }
}
