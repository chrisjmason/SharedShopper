package share;

import data.repos.share.ShareRepository;
import data.repos.share.ShareRepositoryInterface;

public class SharePresenter implements ShareInterface.Presenter {
    ShareInterface.View view;
    ShareRepositoryInterface repository;

    public SharePresenter(ShareInterface.View view){
        this.view = view;
        repository = new ShareRepository(this);
    }

    @Override
    public void updateCode(String newCode) {
        repository.changeShareCode(newCode);
    }

    @Override
    public void codeChanged() {
        view.codeChanged();
    }

    @Override
    public void codeNotChanged(String error) {
        view.codeNotChanged(error);
    }
}
