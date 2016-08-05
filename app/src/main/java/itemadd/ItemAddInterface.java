package itemadd;

public interface ItemAddInterface {

    interface Presenter {
        void add(String title, String desc, int colour);
    }

    interface View{
        void finishActivity();
    }
}
