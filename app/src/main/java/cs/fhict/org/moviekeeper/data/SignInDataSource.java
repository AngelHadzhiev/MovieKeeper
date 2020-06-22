package cs.fhict.org.moviekeeper.data;

import com.google.firebase.auth.FirebaseUser;

public interface SignInDataSource {

    interface SignInListener{
        void onSuccess(String message);
        void onFailure(String message);
    }

    void performSignIn( String email, String password,SignInListener mOnLogInListener);


}
