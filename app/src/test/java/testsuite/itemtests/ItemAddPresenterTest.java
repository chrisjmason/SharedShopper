package testsuite.itemtests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import itemadd.ItemAddInterface;
import itemadd.ItemAddPresenter;
import utility.pojo.Item;
import utility.util.CodeUtil;
import utility.util.DateUtil;

public class ItemAddPresenterTest {
    @Mock
    ItemAddInterface.View view;

    ItemAddPresenter presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new ItemAddPresenter(view);
    }

    @Test
    public void showItems_Success(){
        presenter.showItemsView(getFakeList());
        verify(view).finishActivity();
    }

    public List<Item> getFakeList(){
        Item item1 = new Item("test1","test1",000000, DateUtil.getDate(),
                DateUtil.getTimestamp(), CodeUtil.getCode(),"abc123");
        Item item2 = new Item("test2","test2",000000, DateUtil.getDate(),
                DateUtil.getTimestamp(), CodeUtil.getCode(),"abc123");

        List<Item> itemList = new ArrayList<Item>();
        itemList.add(item1);
        itemList.add(item2);

        return itemList;
    }
}
