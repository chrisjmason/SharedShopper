package data.repos.item;

import utility.BasePresenterInterface;

public interface ItemRepositoryInterface {
    void getData();
    void addItem(String title, String desc, int colour);
    void deleteItem(int position);
    void getItem(int position);
    void getLocalData();
}
