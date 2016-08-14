package login;

public interface LoginInterface {

    interface Presenter{
        void checkLogin();
        void login(String username, String password);
        void register(String username, String password);
        void loginOk();
        void loginBad(String reason);
        void attachView(LoginInterface.View view);
    }

    interface View{
        void loginOk();
        void createToast(String text);
    }
}
