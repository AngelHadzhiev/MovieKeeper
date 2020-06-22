package cs.fhict.org.moviekeeper.ui.authentication.signup;

import com.google.firebase.auth.FirebaseUser;

import cs.fhict.org.moviekeeper.utils.mvp.IBaseActivity;
import cs.fhict.org.moviekeeper.utils.mvp.IBasePresenter;

public interface SignUpContract {

    interface View  extends IBaseActivity {
        void goToMainActivity();
        void goToLogInActivity();

        void updateUI(FirebaseUser user);
        void setTitleToolbar(int  resId);
    }

    interface  Presenter extends IBasePresenter<View>{

        void onSignUp(String name, String email, String password);
        void onViewLoad();
        void onSignInClicked();

    }

}
