package testsuite.itemtests;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import data.repos.item.ItemRepository;
import data.repos.item.ItemRepositoryInterface;
import data.rx.Interactor;
import rx.Observable;
import rx.schedulers.Schedulers;
import utility.BasePresenterInterface;
import utility.pojo.Item;
import utility.util.CodeUtil;
import utility.util.DateUtil;

public class ItemRepositoryTest {

    private static final String TITLE = "testtitle";
    private static final String DESC = "testdesc";
    private static final int COLOUR = 000000;
    private static final int POSITION = 1;

    @Mock
    BasePresenterInterface presenter;
    @Mock
    Interactor interactor;

    private ItemRepositoryInterface repository;

    private Observable<List<Item>> failureListObs = Observable.error(new IOException());
    private Observable<List<Item>> successListObs = Observable.just(getFakeList())
            .subscribeOn(Schedulers.immediate());
    private Observable<Item> failureItemObs = Observable.error(new IOException());
    private Observable<Item> successItemObs = Observable.just(new Item(TITLE,DESC,COLOUR,null,DateUtil.getTimestamp(),null,null))
            .subscribeOn(Schedulers.immediate());

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        repository = new ItemRepository(presenter, interactor);
    }

    @Test
    public void getData_Success(){
        when(interactor.getListFinalObservable()).thenReturn(successListObs);
        repository.getData();
        verify(presenter).showItemsView(anyList());
    }

    @Test
    public void getData_Failure(){
        when(interactor.getListFinalObservable()).thenReturn(failureListObs);
        repository.getData();
        verify(presenter).showToast(anyString());
    }

    @Test
    public void addItem_Success(){
        when(interactor.addItemFinalObservable(TITLE,DESC,COLOUR)).thenReturn(successListObs);
        repository.addItem(TITLE,DESC,COLOUR);
        verify(presenter).showItemsView(anyList());
        verify(presenter).showToast(anyString());
    }

    @Test
    public void addItem_Failure(){
        when(interactor.addItemFinalObservable(TITLE,DESC,COLOUR)).thenReturn(failureListObs);
        repository.addItem(TITLE,DESC,COLOUR);
        verify(presenter).showToast(anyString());
    }

    @Test
    public void deleteItem_Success(){
        when(interactor.deleteItemFinalObservable(POSITION)).thenReturn(successListObs);
        repository.deleteItem(POSITION);
        verify(presenter).showItemsView(anyList());
        verify(presenter).showToast(anyString());
    }

    @Test
    public void deleteItem_Failure(){
        when(interactor.deleteItemFinalObservable(POSITION)).thenReturn(failureListObs);
        repository.deleteItem(POSITION);
        verify(presenter).showToast(anyString());
    }

    @Test
    public void getItem_Success(){
        when(interactor.getItemFinalObservable(POSITION)).thenReturn(successItemObs);
        repository.getItem(POSITION);
        verify(presenter).showItemView((Item) anyObject());
    }

    @Test
    public void getItem_Failure(){
        when(interactor.getItemFinalObservable(POSITION)).thenReturn(failureItemObs);
        repository.getItem(POSITION);
        verify(presenter).showToast(anyString());
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
