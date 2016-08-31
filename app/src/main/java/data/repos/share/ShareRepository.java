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
    Interactor interactor;
    SharedPrefHelper sharedPrefHelper;

    public ShareRepository(Interactor interactor, SharedPrefHelper sharedPrefHelper){
        this.interactor = interactor;
        this.sharedPrefHelper = sharedPrefHelper;
    }

    @Override
    public void attachPresenter(ShareInterface.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void changeShareCode(String newCode) {
        Observable<LoginResponse> shareCodeObservable = interactor.getShareObservable(newCode);

        shareCodeObservable.subscribe(new Subscriber<LoginResponse>() {
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
                            sharedPrefHelper.storeDataid(loginResponse.getDataid());
                            presenter.codeChanged();
                        }
                    }
                });
    }
}
