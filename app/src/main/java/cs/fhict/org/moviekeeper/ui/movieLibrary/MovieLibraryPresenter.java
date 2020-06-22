package cs.fhict.org.moviekeeper.ui.movieLibrary;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Objects;

import cs.fhict.org.moviekeeper.R;
import cs.fhict.org.moviekeeper.data.FirebaseDataSource;
import cs.fhict.org.moviekeeper.data.FirebaseRepository;
import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.data.model.User;

public class MovieLibraryPresenter implements MovieLibraryContract.Presenter {
    private MovieLibraryContract.View view;
    private final FirebaseRepository firebaseRepository;
    ArrayList<Movie> moviesList;

    MovieLibraryPresenter(FirebaseRepository firebaseRepository) {

        this.firebaseRepository = firebaseRepository;
    }
    @Override
    public void onViewLoaded() {

        view.setTitleToolbar(R.string.string_movie_library);

    }

    @Override
    public void getMoviesPerUser(String uid) {
        firebaseRepository.getMoviesPerUser(uid, new FirebaseDataSource.FirebaseGetMoviesListener() {
            @Override
            public void onSuccess(User user) {

                view.updateViewData(user.getMyMovies());
            }

            @Override
            public void onFailure(String message) {
                view.ShowToast(message);
            }
        });
    }

    @Override
    public void onDeleteMovie(Movie movie) {
        firebaseRepository.removeMovie(movie, new FirebaseDataSource.FirebaseAddMovieListener() {
            @Override
            public void onSuccess(String message)
            {
                view.ShowToast(movie.getTitle() + movie.getLanguage() + FirebaseAuth.getInstance().getCurrentUser().getUid() );
                view.ShowToast(message);
            }

            @Override
            public void onFailure(String message)
            {
                Log.d("ERROR",message);
                view.ShowToast(message);
            }
        });
    }


    @Override
    public void onViewActive(MovieLibraryContract.View view) {
        this.view=view;
    }

    @Override
    public void onViewInactive() {
        this.view = null;
    }
}
