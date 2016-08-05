package com.sharedshopper.chris.sharedshopper.mock.data;

import java.util.ArrayList;


import data.ItemRepositoryInterface;
import utility.BasePresenterInterface;
import utility.CodeUtil;
import utility.DateUtil;
import utility.Item;

public class MockRepository implements ItemRepositoryInterface {

    private BasePresenterInterface presenter;
    private MockDb data;

    public MockRepository(BasePresenterInterface presenter){
        this.presenter = presenter;
        data = MockDb.getInstance();
    }

    @Override
    public void getData() {
        presenter.showItemsView(data.getData());
    }

    @Override
    public void addItem(String title, String desc, int colour) {
        data.addItem(title, desc, colour);
        presenter.showItemsView(data.getData());
    }

    @Override
    public void deleteItem(int position) {
        data.deleteItem(position);
        presenter.showItemsView(data.getData());
    }

    @Override
    public void getItem(int position) {
        Item item = data.getItem(position);
        presenter.showItemView(item);
    }
}