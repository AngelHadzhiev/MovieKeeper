package cs.fhict.org.moviekeeper.ui.authentication.signin;

import com.google.firebase.auth.FirebaseAuth;

import cs.fhict.org.moviekeeper.R;
import cs.fhict.org.moviekeeper.data.SignInDataSource;
import cs.fhict.org.moviekeeper.data.SignInRepository;
import cs.fhict.org.moviekeeper.data.SignUpRepository;
import cs.fhict.org.moviekeeper.data.remote.FirebaseHelper;

public class SignInPresenter implements SignInContract.Presenter {

    private SignInContract.View view;
    private SignInRepository signInRepository;
    private FirebaseAuth mAuth;

    public SignInPresenter() {
        mAuth = FirebaseAuth.getInstance();
        signInRepository = new SignInRepository(FirebaseHelper.getInstance());
    }

    @Override
    public void onViewLoaded() {
        view.setTitleToolbar(R.string.res_sign_in);
    }

    @Override
    public void onLogIn(String email, String password) {
        signInRepository.performSignIn(email, password, new SignInDataSource.SignInListener() {
            @Override
            public void onSuccess(String message) {
                view.ShowToast(message);
                view.goToMainActivity();
            }

            @Override
            public void onFailure(String message) {
                view.ShowToast(message);
            }
        });
    }

    @Override
    public void onSignUpClicked() {
        view.goToSignUpActivity();
    }

    @Override
    public void onViewActive(SignInContract.View view) {
        this.view = view;
    }

    @Override
    public void onViewInactive() {
        this.view = null;
    }
}
