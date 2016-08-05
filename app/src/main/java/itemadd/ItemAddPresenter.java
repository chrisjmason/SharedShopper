package itemadd;

import com.sharedshopper.chris.sharedshopper.Injector;

import java.util.List;


import data.repos.item.ItemRepositoryInterface;
import utility.BasePresenterInterface;
import utility.pojo.Item;

public class ItemAddPresenter implements ItemAddInterface.Presenter, BasePresenterInterface {
    private ItemRepositoryInterface itemRepository;
    private ItemAddInterface.View view;

    public ItemAddPresenter(ItemAddInterface.View view){
        itemRepository = Injector.provideItemData(this);
        this.view = view;
    }

    @Override
    public void add(String title, String desc, int colour) {
        if(title != null) {
            itemRepository.addItem(title, desc, colour);
        }
    }

    @Override
    public void showItemsView(List<Item> list) {
        view.finishActivity();
    }

    @Override
    public void showItemView(Item item) {}

    @Override
    public void showToast(String text) {}

}
