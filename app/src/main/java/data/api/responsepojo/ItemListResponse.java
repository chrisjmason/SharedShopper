package data.api.responsepojo;

import java.util.ArrayList;
import java.util.List;

import utility.pojo.Item;

public class ItemListResponse {
    List<Item> items;

    public ItemListResponse(){
        items = new ArrayList<>();
    }

    public List<Item> getList(){
        return items;
    }

    public void setList(List<Item> list){
        items = list;
    }
}
