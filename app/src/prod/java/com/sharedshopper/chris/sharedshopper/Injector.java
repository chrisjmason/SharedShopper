package com.sharedshopper.chris.sharedshopper;


import data.repos.item.ItemRepository;
import data.repos.item.ItemRepositoryInterface;
import data.rx.Interactor;
import data.sharedpref.SharedPrefHelper;
import utility.BasePresenterInterface;
import utility.MyApplication;

public class Injector{

    public static ItemRepositoryInterface provideItemData(BasePresenterInterface presenter){
        return new ItemRepository(presenter, new Interactor());
    }
}