package itemdetail;

import com.sharedshopper.chris.sharedshopper.Injector;

import java.util.List;

import data.repos.item.ItemRepositoryInterface;
import utility.BasePresenterInterface;
import utility.pojo.Item;

public class ItemDetailPresenter implements ItemDetailInterface.Presenter, BasePresenterInterface  {
    private ItemRepositoryInterface itemRepository;
    private ItemDetailInterface.View view;

    public ItemDetailPresenter(ItemDetailInterface.View view){
        itemRepository = Injector.provideItemData(this);
        this.view = view;
    }

    @Override
    public void getItem(int position) {
        itemRepository.getItem(position);
    }

    @Override
    public void showToast(String text){}

    @Override
    public void showItemsView(List<Item> list) {}

    @Override
    public void showItemView(Item item) {
        view.setUpDetail(item);
    }

}
