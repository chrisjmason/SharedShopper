package itemdetail;

import utility.pojo.Item;

public interface ItemDetailInterface{
    interface Presenter  {
        void getItem(int position);
    }

    interface View{
        void setUpDetail(Item item);
    }
}
