package share;

import data.repos.share.ShareRepository;
import data.repos.share.ShareRepositoryInterface;

public class SharePresenter implements ShareInterface.Presenter {
    ShareInterface.View view;
    ShareRepositoryInterface repository = null;

    public SharePresenter(ShareInterface.View view, ShareRepositoryInterface repository){
        this.view = view;
        this.repository = repository;
        repository.attachPresenter(this);
    }

    @Override
    public void updateCode(String newCode) {
        if(repository!=null) repository.changeShareCode(newCode);
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
