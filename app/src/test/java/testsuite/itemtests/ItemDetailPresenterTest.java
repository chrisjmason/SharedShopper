package testsuite.itemtests;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;

import itemdetail.ItemDetailInterface;
import itemdetail.ItemDetailPresenter;
import utility.pojo.Item;
import utility.util.CodeUtil;
import utility.util.DateUtil;

public class ItemDetailPresenterTest {
    ItemDetailPresenter presenter;

    Item item1 = new Item("test1","test1",000000, DateUtil.getDate(),
            DateUtil.getTimestamp(), CodeUtil.getCode(),"abc123");

    @Mock
    ItemDetailInterface.View view;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new ItemDetailPresenter(view);
    }

    @Test
    public void showItem_Success(){
        presenter.showItemView(item1);
        verify(view).setUpDetail((Item)anyObject());

    }
}
