package data.db;

import java.util.List;

import utility.pojo.Item;

public interface ItemDbHelperInterface {
    List<Item> addItem(Item item, int onlineBool);
    void deleteItem(int position);
    Item getItem(int position);
    void updateData(List<Item> itemList);
}

