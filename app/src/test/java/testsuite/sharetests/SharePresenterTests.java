package testsuite.sharetests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

import data.repos.share.ShareRepositoryInterface;
import share.ShareInterface;
import share.SharePresenter;


public class SharePresenterTests {
    private static final String CODE = "abc123";
    private static final String ERROR = "error";

    @Mock
    ShareInterface.View view;
    @Mock
    ShareRepositoryInterface repository;

    SharePresenter presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new SharePresenter(view, repository);
    }

    @Test
    public void updateCode_Success(){
        presenter.updateCode(CODE);
        verify(repository).changeShareCode(CODE);
    }

    @Test
    public void codeChanged_Success(){
        presenter.codeChanged();
        verify(view).codeChanged();
    }

    @Test
    public void codeNotChanged_Success(){
        presenter.codeNotChanged(ERROR);
        verify(view).codeNotChanged(ERROR);
    }
}
