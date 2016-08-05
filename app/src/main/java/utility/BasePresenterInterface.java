package utility;

import java.util.List;

import utility.pojo.Item;

public interface BasePresenterInterface {
    void showItemsView(List<Item> list);
    void showItemView(Item item);
    void showToast(String text);
}
