package cs.fhict.org.moviekeeper.data;

import com.google.firebase.auth.FirebaseUser;

import cs.fhict.org.moviekeeper.data.remote.FirebaseHelper;

public class SignUpRepository implements SignUpDataSource {
    private FirebaseHelper firebaseHelper;
    private static SignUpRepository instance = null;

    public SignUpRepository(FirebaseHelper firebaseHelper) {
        this.firebaseHelper= firebaseHelper;
    }
    public static SignUpRepository getInstance(FirebaseHelper firebaseHelper) {
        if (instance == null) {
            instance = new SignUpRepository(firebaseHelper);
        }
        return instance;
    }

    @Override
    public void performRegistrationWithEmailAndPassword(String name,String email, String password,final RegisterListener mOnRegistrationListener) {
        firebaseHelper.performRegistrationWithEmailAndPassword(name, email, password, new RegisterListener() {
            @Override
            public void onSuccess(FirebaseUser firebaseUser) {
                mOnRegistrationListener.onSuccess(firebaseUser);
            }

            @Override
            public void onFailure(String message) {
                mOnRegistrationListener.onFailure(message);
            }
        });
    }

    @Override
    public void insertUserIntoDatabase(String name, String email, String uid) {

        firebaseHelper.insertUserIntoDatabase( name, email, uid);

    }
}
