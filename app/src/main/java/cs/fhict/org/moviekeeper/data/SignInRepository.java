package cs.fhict.org.moviekeeper.data;

import cs.fhict.org.moviekeeper.data.remote.FirebaseHelper;

public class SignInRepository implements SignInDataSource {

    private FirebaseHelper firebaseHelper;
    private static SignInRepository instance = null;

    public SignInRepository(FirebaseHelper firebaseHelper) {
        this.firebaseHelper= firebaseHelper;
    }
    public static SignInRepository getInstance(FirebaseHelper firebaseHelper) {
        if (instance == null) {
            instance = new SignInRepository(firebaseHelper);
        }
        return instance;
    }

    @Override
    public void performSignIn( String email, String password, SignInListener mOnLogInListener) {
        firebaseHelper.performSignIn(email, password, new SignInListener() {
            @Override
            public void onSuccess(String message) {
                mOnLogInListener.onSuccess(message);
            }

            @Override
            public void onFailure(String message) {
                mOnLogInListener.onFailure(message);
            }
        });
    }
}
