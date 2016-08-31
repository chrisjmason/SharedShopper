package data.repos.share;

import share.ShareInterface;

public interface ShareRepositoryInterface {
    void changeShareCode(String newCode);
    void attachPresenter(ShareInterface.Presenter presenter);
}
