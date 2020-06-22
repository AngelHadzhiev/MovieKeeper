package cs.fhict.org.moviekeeper.data;

import android.util.Log;

import java.util.ArrayList;

import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.data.model.User;
import cs.fhict.org.moviekeeper.data.remote.FirebaseHelper;

public class FirebaseRepository implements FirebaseDataSource {
    private FirebaseHelper firebaseHelper;
    private static FirebaseRepository instance = null;
    ArrayList<Movie> movies = new ArrayList<>();

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public FirebaseRepository(FirebaseHelper firebaseHelper) {
        this.firebaseHelper= firebaseHelper;
    }
    public static FirebaseRepository getInstance(FirebaseHelper firebaseHelper) {
        if (instance == null) {
            instance = new FirebaseRepository(firebaseHelper);
        }
        return instance;
    }
    @Override
    public void addMovieToUserLibrary(Movie movie, FirebaseAddMovieListener listener) {
        firebaseHelper.addMovieToUserLibrary(movie, new FirebaseAddMovieListener() {
            @Override
            public void onSuccess(String message) {
                listener.onSuccess(message);
            }

            @Override
            public void onFailure(String message) {
                listener.onFailure(message);
            }
        });
    }

    @Override
    public void getMoviesPerUser(String uid, FirebaseGetMoviesListener listener) {
        firebaseHelper.getMoviesPerUser(uid, new FirebaseGetMoviesListener() {
            @Override
            public void onSuccess(User user) {

                  //  Log.d("onSuccessMovies",user.getMovies().toString());
                Log.d("SNAP",user.getName());
                listener.onSuccess(user);
            }

            @Override
            public void onFailure(String message) {
                listener.onFailure(message);
            }
        });
    }

    @Override
    public void getHighestRatingMovies(String uid, FirebaseGetMoviesListener listener) {
        firebaseHelper.getHighestRatingMovies(uid, new FirebaseGetMoviesListener() {
            @Override
            public void onSuccess(User user) {

                //  Log.d("onSuccessMovies",user.getMovies().toString());
                Log.d("SNAP",user.getName());
                movies = user.getMyMovies();
                listener.onSuccess(user);
            }

            @Override
            public void onFailure(String message) {
                listener.onFailure(message);
            }
        });
    }

    @Override
    public void getLasAdded(String uid, FirebaseGetMoviesListener listener) {
        firebaseHelper.getLasAdded(uid, new FirebaseGetMoviesListener() {
            @Override
            public void onSuccess(User user) {

                //  Log.d("onSuccessMovies",user.getMovies().toString());
                Log.d("SNAP",user.getName());
                movies = user.getMyMovies();
                listener.onSuccess(user);
            }

            @Override
            public void onFailure(String message) {
                listener.onFailure(message);
            }
        });
    }

    @Override
    public void removeMovie(Movie movie, FirebaseAddMovieListener listener) {
        firebaseHelper.removeMovie(movie, new FirebaseAddMovieListener() {
            @Override
            public void onSuccess(String message) {
                listener.onSuccess(message);
            }

            @Override
            public void onFailure(String message) {
                    listener.onFailure(message);
            }
        });
    }
}
