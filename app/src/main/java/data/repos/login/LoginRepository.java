package data.repos.login;

import data.api.responsepojo.LoginResponse;
import data.rx.Interactor;
import data.sharedpref.SharedPrefHelper;
import data.sharedpref.SharedPrefHelperInterface;
import login.LoginInterface;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utility.MyApplication;
import utility.pojo.User;

public class LoginRepository implements LoginRepositoryInterface {
    Interactor interactor;
    LoginInterface.Presenter presenter;
    SharedPrefHelperInterface sharedPrefHelper;

    public LoginRepository(LoginInterface.Presenter presenter, Interactor interactor,
                           SharedPrefHelper sharedPrefHelper){
        this.presenter = presenter;
        this.interactor = interactor;
        this.sharedPrefHelper = sharedPrefHelper;
    }

    @Override
    public void registerUser(User user) {
        Observable<LoginResponse> registerObservable = interactor.getRegisterObservable(user);

        registerObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        presenter.loginBad("rx error");
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        if(!loginResponse.getError()){
                            sharedPrefHelper.storeUserInfo(loginResponse.getApikey(),loginResponse.getDataid());
                            presenter.loginOk();
                        }else if(loginResponse.getError()){
                            presenter.loginBad(loginResponse.getMessage());
                        }
                    }
                });
    }

    @Override
    public void loginUser(User user) {
        Observable<LoginResponse> loginObservable = interactor.getLoginObservable(user);

        loginObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        if(!loginResponse.getError()){
                            sharedPrefHelper.storeUserInfo(loginResponse.getApikey(),loginResponse.getDataid());
                            presenter.loginOk();
                        }else if(loginResponse.getError()){
                            presenter.loginBad(loginResponse.getMessage());
                        }
                    }
                });
    }
}
