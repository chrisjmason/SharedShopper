package share;

public interface ShareInterface {
    interface Presenter{
        void updateCode(String newCode);
        void codeChanged();
        void codeNotChanged(String error);
    }

    interface View{
        void codeChanged();
        void codeNotChanged(String error);
    }
}
