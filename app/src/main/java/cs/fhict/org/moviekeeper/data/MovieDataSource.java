package cs.fhict.org.moviekeeper.data;


import java.util.ArrayList;

import cs.fhict.org.moviekeeper.data.model.Movie;

public interface MovieDataSource {
    interface LoadMovieCallBack{
        void onMoviesLoaded(Movie movie);
        void onDataNotAvailable();
        void onError(Throwable t);

    }

    void getMovies(String title, String apiKey, LoadMovieCallBack callback);//,LoadMovieCallBack callback);

}
