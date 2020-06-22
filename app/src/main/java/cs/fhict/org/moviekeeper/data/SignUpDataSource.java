package cs.fhict.org.moviekeeper.data;

import com.google.firebase.auth.FirebaseUser;

public interface SignUpDataSource {

    interface RegisterListener{
        void onSuccess(FirebaseUser firebaseUser);
        void onFailure(String message);
    }


    void performRegistrationWithEmailAndPassword(String name, String email, String password,RegisterListener mOnRegistrationListener);
    void insertUserIntoDatabase(String name, String email, String uid);
}
