package cs.fhict.org.moviekeeper.data.remote;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import cs.fhict.org.moviekeeper.data.FirebaseDataSource;
import cs.fhict.org.moviekeeper.data.SignInDataSource;
import cs.fhict.org.moviekeeper.data.SignUpDataSource;
import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.data.model.User;

public class FirebaseHelper  implements SignUpDataSource, SignInDataSource, FirebaseDataSource {

    private static FirebaseHelper INSTANCE;
    private FirebaseAuth mAuth;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public static FirebaseHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseHelper();
        }
        return INSTANCE;
    }

    public FirebaseHelper() {

        mAuth = FirebaseAuth.getInstance();
    }
    //private SignUpDataSource.RegisterListener mOnRegistrationListener;

    public void performRegistrationWithEmailAndPassword(String name, String email, String password,RegisterListener mOnRegistrationListener) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(  new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            if (mAuth.getCurrentUser()!= null) {
                                Log.d("DISPLAY NAME",name);

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                                mAuth.getCurrentUser().updateProfile(profileUpdates).addOnCompleteListener(t->{
                                    Log.d("DISPLAY NAME","UPDATED" + name);
                                })
                                .addOnFailureListener(t->{
                                    Log.d("DISPLAY NAME","ERROR" + t.getMessage());
                                });

                                mOnRegistrationListener.onSuccess(Objects.requireNonNull(task.getResult()).getUser());
                            }

                        }
                        else {
                            mOnRegistrationListener.onFailure(Objects.requireNonNull(task.getException()).getMessage());
                        }
                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mOnRegistrationListener.onFailure(e.getMessage());
                    }
                });

    }

    @Override
    public void insertUserIntoDatabase(String name, String email, String uid) {

        Map<String,Object> userData = new HashMap<>();

        userData.put("name",name);
        userData.put("email",email);
        userData.put("uid",uid);
        userData.put("myMovies", new ArrayList<Movie>());


        firebaseFirestore.collection("users").document(uid).set(userData)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //SEND MESSAGE
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //SEND MESSAGE
            }
        });


    }


    @Override
    public void performSignIn(String email, String password, SignInListener mOnLogInListener) {
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mOnLogInListener.onSuccess(task.toString() + "SUCCESS"); //SEND SUCCESS MESSAGE
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mOnLogInListener.onFailure(e.getMessage());
                    }
                });

    }


    @Override
    public void addMovieToUserLibrary(Movie movie, FirebaseAddMovieListener listener) {

        firebaseFirestore.collection("users").document(Objects.requireNonNull(mAuth.getCurrentUser()).getUid()).update("myMovies", FieldValue.arrayUnion(movie))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listener.onSuccess("SUCCESS");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onFailure(e.getMessage() + e + e.getLocalizedMessage());
                    }
                });

    }

    @Override
    public void getMoviesPerUser(String uid, FirebaseGetMoviesListener listener) {

        DocumentReference docRef = firebaseFirestore.collection("users").document(uid);
        //docRef.get()
        docRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                User user  = document.toObject(User.class);
                                listener.onSuccess(user);
                                Log.d("User", "DocumentSnapshot data: " + document.getData());

                            } else {
                                Log.d("User", "No such document");
                            }
                        } else {
                            Log.d("User", "get failed with ", task.getException());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onFailure(e.getMessage());
                    }
                });
    }

    @Override
    public void getHighestRatingMovies(String uid, FirebaseGetMoviesListener listener) {
        DocumentReference docRef = firebaseFirestore.collection("users").document(uid);
        //docRef.get()
        docRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                User user  = document.toObject(User.class);
                                listener.onSuccess(user);
                                Log.d("User", "DocumentSnapshot data: " + document.getData());

                            } else {
                                Log.d("User", "No such document");
                            }
                        } else {
                            Log.d("User", "get failed with ", task.getException());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onFailure(e.getMessage());
                    }
                });
    }

    @Override
    public void getLasAdded(String uid, FirebaseGetMoviesListener listener) {
        DocumentReference docRef = firebaseFirestore.collection("users").document(uid);
        //docRef.get()
        docRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                User user  = document.toObject(User.class);
                                listener.onSuccess(user);
                                Log.d("User", "DocumentSnapshot data: " + document.getData());

                            } else {
                                Log.d("User", "No such document");
                            }
                        } else {
                            Log.d("User", "get failed with ", task.getException());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onFailure(e.getMessage());
                    }
                });
    }

    @Override
    public void removeMovie(Movie movie, FirebaseAddMovieListener listener) {
        Log.d("Movieeee",movie.getTitle());
        String s = mAuth.getCurrentUser().getUid().toString();
        Log.d("AUTH",s);
        firebaseFirestore.collection("users").document(Objects.requireNonNull(s)).update("myMovies", FieldValue.arrayRemove(movie))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listener.onSuccess("SUCCESS");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onFailure(e.getMessage() + e + e.getLocalizedMessage());
                    }
                });

    }
}
