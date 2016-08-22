package itemsoverview;

import com.sharedshopper.chris.sharedshopper.Injector;
import java.util.List;
import data.repos.item.ItemRepositoryInterface;
import utility.BasePresenterInterface;
import utility.pojo.Item;

public class ItemOverviewPresenter implements ItemOverviewInterface.Presenter, BasePresenterInterface{

    private ItemRepositoryInterface itemRepository;
    private ItemOverviewInterface.View view;

    public ItemOverviewPresenter(ItemOverviewInterface.View view) {
        this.view = view;
        itemRepository = Injector.provideItemData(this);
    }

    @Override
    public void addNewItem() {
        view.goToAdd();
    }

    @Override
    public void showItem(int position) {
        view.goToDetail(position);
    }

    @Override
    public void deleteItem(int position) {
        itemRepository.deleteItem(position);
    }

    @Override
    public void loadItems() {
        itemRepository.getLocalData();
        itemRepository.getData();
    }


    @Override
    public void showItemsView(List<Item> items) {
        view.updateView(items);
    }

    @Override
    public void showToast(String text) {
        view.showToast(text);
    }

    @Override
    public void showItemView(Item item) {}
}
