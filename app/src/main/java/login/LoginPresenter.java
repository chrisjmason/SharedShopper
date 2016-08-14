package login;

import data.repos.login.LoginRepository;
import data.repos.login.LoginRepositoryInterface;
import data.rx.Interactor;
import data.sharedpref.SharedPrefHelper;
import utility.MyApplication;
import utility.pojo.User;

public class LoginPresenter implements LoginInterface.Presenter {
    LoginInterface.View view;
    LoginRepositoryInterface repository;
    SharedPrefHelper sharedPrefHelper;

    public LoginPresenter(SharedPrefHelper sharedPrefHelper){
        this.sharedPrefHelper = sharedPrefHelper;
    }

    @Override
    public void attachView(LoginInterface.View view) {
        this.view = view;
        repository = new LoginRepository(this, new Interactor(),sharedPrefHelper );
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void checkLogin(){
        if(sharedPrefHelper.checkSharedPref()){
            loginOk();
        }
    }

    @Override
    public void login(String username, String password) {
        //for testing
        if(username=="christest" && password=="christest"){
            loginOk();
        }else if(sharedPrefHelper.checkSharedPref()){
            loginOk();
        }else{
            repository.loginUser(new User(username, password, null, null));
        }
    }

    @Override
    public void register(String username, String password) {
        if(username.length()<=6) {
            loginBad("Username must be 6 or more characters");
        }else if(password.length()<=8){
            loginBad("Password must be 8 or more characters");
        }else{
            repository.registerUser(new User(username, password, null, null));
        }
    }

    @Override
    public void loginOk(){
        view.loginOk();
    }

    @Override
    public void loginBad(String reason){
        view.createToast(reason);
    }


}
