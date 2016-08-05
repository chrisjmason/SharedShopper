package login;

import data.repos.login.LoginRepository;
import data.repos.login.LoginRepositoryInterface;
import data.sharedpref.SharedPrefHelper;
import utility.MyApplication;
import utility.pojo.User;

public class LoginPresenter implements LoginInterface.Presenter {
    LoginInterface.View view;
    LoginRepositoryInterface repository;
    SharedPrefHelper sharedPrefHelper;

    public LoginPresenter(LoginInterface.View view){
        this.view = view;
        repository = new LoginRepository(this);
        sharedPrefHelper = new SharedPrefHelper(MyApplication.getContext());
    }

    @Override
    public void checkLogin(){
        if(sharedPrefHelper.checkSharedPref()){
            loginOk();
        }
    }

    @Override
    public void login(String username, String password) {
        if(sharedPrefHelper.checkSharedPref()){
            loginOk();
        }else{
            repository.loginUser(new User(username, password, null, null));
        }
    }

    @Override
    public void register(String username, String password) {
        repository.registerUser(new User(username, password,null,null));
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
