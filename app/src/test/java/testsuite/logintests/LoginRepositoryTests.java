package testsuite.logintests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import data.api.responsepojo.LoginResponse;
import data.repos.login.LoginRepository;
import data.repos.login.LoginRepositoryInterface;
import data.rx.Interactor;
import data.sharedpref.SharedPrefHelperInterface;
import login.LoginInterface;
import rx.Observable;
import rx.schedulers.Schedulers;
import utility.pojo.User;

public class LoginRepositoryTests {
    private static final String USERNAME = "testusername";
    private static final String PASSWORD = "testpassword";
    private static final String APIKEY = "abc123";
    private static final String DATAID = "abc123";
    private static final String SUCCESS_STRING = "login successful";
    private static final String FAILURE_STRING = "login failed";
    private static final String ERROR_STRING = "Error, please try again.";

    @Mock
    LoginInterface.Presenter presenter;
    @Mock
    Interactor interactor;
    @Mock
    SharedPrefHelperInterface sharedPref;

    private LoginRepositoryInterface loginRepository;
    User user = new User(USERNAME,PASSWORD,APIKEY,DATAID);

    Observable<LoginResponse> testObsSuccess = Observable.just(new LoginResponse(false,SUCCESS_STRING,APIKEY,DATAID))
            .subscribeOn(Schedulers.immediate());

    Observable<LoginResponse> testObsFailure = Observable.just(new LoginResponse(true,FAILURE_STRING,APIKEY,DATAID))
            .subscribeOn(Schedulers.immediate());

    Observable<LoginResponse> testObsError = Observable.error(new IOException());

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        loginRepository = new LoginRepository(presenter,interactor,sharedPref);
    }

    @Test
    public void login_Success(){
        when(interactor.getLoginObservable(user)).thenReturn(testObsSuccess);
        loginRepository.loginUser(user);
        verify(sharedPref).storeUserInfo(APIKEY,DATAID);
        verify(presenter).loginOk();
    }

    @Test
    public void login_Failure(){
        when(interactor.getLoginObservable(user)).thenReturn(testObsFailure);
        loginRepository.loginUser(user);
        verify(presenter).loginBad(FAILURE_STRING);
    }

    @Test
    public void login_Error(){
        when(interactor.getLoginObservable(user)).thenReturn(testObsError);
        loginRepository.loginUser(user);
        verify(presenter).loginBad(ERROR_STRING);
    }

    @Test
    public void register_Success(){
        when(interactor.getRegisterObservable(user)).thenReturn(testObsSuccess);
        loginRepository.registerUser(user);
        verify(sharedPref).storeUserInfo(APIKEY,DATAID);
        verify(presenter).loginOk();
    }

    @Test
    public void register_Failure(){
        when(interactor.getRegisterObservable(user)).thenReturn(testObsFailure);
        loginRepository.registerUser(user);
        verify(presenter).loginBad(FAILURE_STRING);
    }

    @Test
    public void register_Error(){
        when(interactor.getRegisterObservable(user)).thenReturn(testObsError);
        loginRepository.registerUser(user);
        verify(presenter).loginBad(ERROR_STRING);
    }

}
