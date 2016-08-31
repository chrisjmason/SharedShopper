package testsuite.sharetests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;

import data.api.responsepojo.LoginResponse;
import data.repos.share.ShareRepository;
import data.rx.Interactor;
import data.sharedpref.SharedPrefHelper;
import rx.Observable;
import rx.schedulers.Schedulers;
import share.SharePresenter;

public class ShareRepositoryTests {
    @Mock
    SharePresenter presenter;
    @Mock
    Interactor interactor;
    @Mock
    SharedPrefHelper sharedPrefHelper;

    ShareRepository shareRepository;

    private static final String SUCCESS = "success";
    private static final String ERROR = "failure";
    private static final String API_KEY = "abc123";
    private static final String DATA_ID = "def456";
    private static final String CODE = "ghi789";

    Observable<LoginResponse> obsError = Observable.error(new IOException());
    Observable<LoginResponse> obsSuccess = Observable.just(new LoginResponse(false,SUCCESS,API_KEY,DATA_ID))
            .subscribeOn(Schedulers.immediate());
    Observable<LoginResponse> obsFailure = Observable.just(new LoginResponse(true,ERROR,null,null))
            .subscribeOn(Schedulers.immediate());

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        shareRepository = new ShareRepository(interactor,sharedPrefHelper);
        shareRepository.attachPresenter(presenter);
    }

    @Test
    public void changeShareCode_Success(){
        when(interactor.getShareObservable(CODE)).thenReturn(obsSuccess);
        shareRepository.changeShareCode(CODE);
        verify(sharedPrefHelper).storeDataid(DATA_ID);
        verify(presenter).codeChanged();
    }

    @Test
    public void changeShareCode_Failure(){
        when(interactor.getShareObservable(CODE)).thenReturn(obsFailure);
        shareRepository.changeShareCode(CODE);
        verify(presenter).codeNotChanged(ERROR);
    }

    @Test
    public void changeShareCode_Error(){
        when(interactor.getShareObservable(CODE)).thenReturn(obsError);
        shareRepository.changeShareCode(CODE);
        verify(presenter).codeNotChanged(anyString());
    }
}
