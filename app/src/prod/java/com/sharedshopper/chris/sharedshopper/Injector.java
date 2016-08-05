package com.sharedshopper.chris.sharedshopper;


import data.repos.item.ItemRepository;
import data.repos.item.ItemRepositoryInterface;
import utility.BasePresenterInterface;

public class Injector{

    public static ItemRepositoryInterface provideItemData(BasePresenterInterface presenter){
        return new ItemRepository(presenter);
    }
}