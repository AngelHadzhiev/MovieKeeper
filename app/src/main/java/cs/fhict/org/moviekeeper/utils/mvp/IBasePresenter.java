package cs.fhict.org.moviekeeper.utils.mvp;

public interface IBasePresenter<View> {
    void onViewActive(View view);
    void onViewInactive();
}
