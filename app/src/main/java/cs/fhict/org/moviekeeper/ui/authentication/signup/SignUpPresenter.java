package cs.fhict.org.moviekeeper.ui.authentication.signup;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;
import java.util.concurrent.Executor;

import androidx.annotation.NonNull;
import cs.fhict.org.moviekeeper.R;
import cs.fhict.org.moviekeeper.data.SignUpDataSource;
import cs.fhict.org.moviekeeper.data.SignUpRepository;
import cs.fhict.org.moviekeeper.data.remote.FirebaseHelper;

public class SignUpPresenter implements SignUpContract.Presenter {
    SignUpContract.View view;
    private SignUpRepository signUpRepository;

    private FirebaseAuth mAuth;

    public SignUpPresenter() {
        mAuth = FirebaseAuth.getInstance();
        signUpRepository = new SignUpRepository(FirebaseHelper.getInstance());
    }

    @Override
    public void onSignUp(String name, String email, String password) {

        signUpRepository.performRegistrationWithEmailAndPassword(name,email, password, new SignUpDataSource.RegisterListener() {
            @Override
            public void onSuccess(FirebaseUser firebaseUser) {
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (firebaseUser != null) {
                    //find a way to get it with firebase instead of name
                    signUpRepository.insertUserIntoDatabase(name,firebaseUser.getEmail(),firebaseUser.getUid());
                    view.goToMainActivity();
                }


            }

            @Override
            public void onFailure(String message) {
                view.ShowToast(message);
                Log.d("USER NAME ERROR", message);
            }
        });


}

    @Override
    public void onViewLoad() {
        view.setTitleToolbar(R.string.res_sign_up);
    }

    @Override
    public void onSignInClicked() {
        view.goToLogInActivity();
    }

    @Override
    public void onViewActive(SignUpContract.View view) {
        this.view = view;
    }

    @Override
    public void onViewInactive() {
        this.view = null;
    }
}
