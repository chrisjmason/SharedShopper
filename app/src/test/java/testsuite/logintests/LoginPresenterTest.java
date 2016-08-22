package testsuite.logintests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import data.repos.login.LoginRepositoryInterface;
import data.sharedpref.SharedPrefHelperInterface;
import login.LoginInterface;
import login.LoginPresenter;

/**
 * Created by Chris on 22/08/2016.
 */
public class LoginPresenterTest {
    private final static String USERNAME_OK = "testusername";
    private final static String PASSWORD_OK = "testpassword";
    private final static String USERNAME_BAD = "testu";
    private final static String PASSWORD_BAD = "testp";


    @Mock
    LoginRepositoryInterface repository;
    @Mock
    LoginInterface.View view;
    @Mock
    SharedPrefHelperInterface sharedPref;

    LoginInterface.Presenter presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenter(sharedPref);
        presenter.attachView(view);
    }

    @Test
    public void login_DetailsSaved(){
        when(sharedPref.checkSharedPref()).thenReturn(true);
        presenter.login(USERNAME_OK,PASSWORD_OK);
        verify(view).loginOk();
    }

    @Test
    public void register_InvalidDetails(){
        presenter.register(USERNAME_BAD, PASSWORD_BAD);
        verify(view).createToast(anyString());
    }
}
