package cs.fhict.org.moviekeeper.ui.movieDetails;

import android.util.Log;

import cs.fhict.org.moviekeeper.data.FirebaseDataSource;
import cs.fhict.org.moviekeeper.data.FirebaseRepository;
import cs.fhict.org.moviekeeper.data.MovieDataSource;
import cs.fhict.org.moviekeeper.data.MoviesRepository;
import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.data.remote.FirebaseHelper;
import cs.fhict.org.moviekeeper.data.remote.MovieRemoteDataSource;
import cs.fhict.org.moviekeeper.utils.constant.Constant;

public class MovieDetailsPresenter implements  MovieDetailsContract.Presenter{

    MovieDetailsContract.View view;
    MoviesRepository moviesRepository;
    FirebaseRepository firebaseRepository;
    public MovieDetailsPresenter() {
        moviesRepository = new MoviesRepository(MovieRemoteDataSource.getInstance());
        firebaseRepository = new FirebaseRepository(FirebaseHelper.getInstance());
    }
    @Override
    public void getMoviesByTitle(String title) {
        moviesRepository.getMovies(title, Constant.apiKey, new MovieDataSource.LoadMovieCallBack() {
            @Override
            public void onMoviesLoaded(Movie movies) {
                view.ShowToast(movies.getTitle() + "TITLE");
                view.updateView(movies);
                view.addMovieToLibrary(movies);
            }

            @Override
            public void onDataNotAvailable() {
                view.ShowToast("No data");
            }

            @Override
            public void onError(Throwable t) {
                view.ShowToast("Error" +  t.getMessage());
                Log.d("ERROR",t.getMessage() + t.getLocalizedMessage());
            }
        });
        //view.ShowToast("Movie Title: " + title);
    }

    @Override
    public void onAddMovieToLibrary(Movie movie) {
           Log.d("Mrrrr",movie.getTitle());
                firebaseRepository.addMovieToUserLibrary(movie, new FirebaseDataSource.FirebaseAddMovieListener() {
                    @Override
                    public void onSuccess(String message) {
                        view.ShowToast(message);
                        Log.d("SUCCESS",message);
                    }

                    @Override
                    public void onFailure(String message) {
                        view.ShowToast(message);
                        Log.d("ERROR",message);
                    }
                });
    }

    @Override
    public void onViewActive(MovieDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void onViewInactive() {
        this.view = null;
    }
}
