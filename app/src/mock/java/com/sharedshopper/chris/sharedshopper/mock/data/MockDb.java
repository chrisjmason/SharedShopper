package com.sharedshopper.chris.sharedshopper.mock.data;

import java.util.ArrayList;
import java.util.List;

import utility.pojo.Item;
import utility.util.CodeUtil;
import utility.util.DateUtil;

public class MockDb {
    private static List<Item> itemList;

    private static MockDb instance;

    private MockDb(){
        itemList = new ArrayList<>();
    }

    public static MockDb getInstance(){
        if(instance == null){
            instance = new MockDb();
        }
        return instance;
    }

    public List<Item> getData(){
        return itemList;
    }

    public void addItem(String title, String desc, int colour){
        itemList.add(new Item(title,desc,colour, DateUtil.getDate(),DateUtil.getTimestamp(), CodeUtil.getCode(), null));
    }

    public void deleteItem(int position){
        itemList.remove(position);
    }

    public Item getItem(int position){
        return itemList.get(position);
    }
}
