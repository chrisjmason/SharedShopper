package com.sharedshopper.chris.sharedshopper;



import com.sharedshopper.chris.sharedshopper.mock.data.MockRepository;

import data.repos.item.ItemRepositoryInterface;
import utility.BasePresenterInterface;

public class Injector{

    public static ItemRepositoryInterface provideItemData(BasePresenterInterface presenter){
        return new MockRepository(presenter);
    }
}