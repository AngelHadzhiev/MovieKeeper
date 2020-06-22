package cs.fhict.org.moviekeeper.ui.authentication.signin;

import cs.fhict.org.moviekeeper.utils.mvp.IBaseActivity;
import cs.fhict.org.moviekeeper.utils.mvp.IBasePresenter;

public interface SignInContract {

    interface  View extends IBaseActivity{
        void goToSignUpActivity();
        void goToMainActivity();
        void setTitleToolbar(int resId);

    }

    interface Presenter extends IBasePresenter<View>{
        void onViewLoaded();
        void onLogIn(String email, String password);
        void onSignUpClicked();

    }
}
