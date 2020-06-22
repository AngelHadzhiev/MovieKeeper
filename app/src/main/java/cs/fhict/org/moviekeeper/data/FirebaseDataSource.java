package cs.fhict.org.moviekeeper.data;

import java.util.ArrayList;

import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.data.model.User;

public interface FirebaseDataSource {
    interface FirebaseAddMovieListener {
        void onSuccess(String message);
        void onFailure(String message);
    }
    interface FirebaseGetMoviesListener {
        void onSuccess(User user);
        void onFailure(String message);
    }
    void addMovieToUserLibrary(Movie movie, FirebaseAddMovieListener listener);
    void getMoviesPerUser(String uid, FirebaseGetMoviesListener listener);
    void getHighestRatingMovies(String uid, FirebaseGetMoviesListener listener);
    void getLasAdded(String uid, FirebaseGetMoviesListener listener);
    void removeMovie(Movie movie, FirebaseAddMovieListener listener);

}
