package itemsoverview;

import java.util.List;

import utility.pojo.Item;

public interface ItemOverviewInterface {

    interface Presenter {
        void addNewItem();

        void showItem(int position);

        void deleteItem(int position);

        void loadItems();
    }

    interface View{
        void updateView(List<Item> list);

        void goToAdd();

        void goToDetail(int position);

        void showToast(String text);
    }
}
