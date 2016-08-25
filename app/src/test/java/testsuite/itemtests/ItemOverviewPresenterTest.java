package testsuite.itemtests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;

import itemsoverview.ItemOverviewInterface;
import itemsoverview.ItemOverviewPresenter;
import utility.pojo.Item;

public class ItemOverviewPresenterTest {
    private static final int POSITION = 1;
    private static final String TOAST_TEXT = "test text";

    @Mock
    ItemOverviewInterface.View view;

    private ItemOverviewPresenter presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new ItemOverviewPresenter(view);
    }

    @Test
    public void clickAdd_Success(){
        presenter.addNewItem();
        verify(view).goToAdd();
    }

    @Test
    public void clickDetail_Success(){
        presenter.showItem(POSITION);
        verify(view).goToDetail(POSITION);
    }

    @Test
    public void showItems_Success(){
        presenter.showItemsView(new ArrayList<Item>());
        verify(view).updateView(anyList());
    }

    @Test
    public void showToast_Success(){
        presenter.showToast(TOAST_TEXT);
        verify(view).showToast(TOAST_TEXT);
    }
}
