package cs.fhict.org.moviekeeper.data;

import android.util.Log;

import java.util.ArrayList;

import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.data.remote.ApiClient;
import cs.fhict.org.moviekeeper.data.remote.ApiInterface;
import cs.fhict.org.moviekeeper.data.remote.MovieRemoteDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository  implements MovieDataSource {

    private static MoviesRepository instance = null;
    private final MovieDataSource movieRemoteDataSource;

    public MoviesRepository (MovieDataSource movieRemoteDataSource) {
      //  service = ApiClient.createService(ApiInterface.class);
        this.movieRemoteDataSource =  movieRemoteDataSource;
    }



    public static MoviesRepository getInstance(MovieDataSource movieRemoteDataSource) {
        if (instance == null) {
            instance = new MoviesRepository(movieRemoteDataSource);
        }
        return instance;
    }


    @Override
    public void getMovies(String title, String apiKey, final LoadMovieCallBack callback) {

        movieRemoteDataSource.getMovies(title, apiKey, new LoadMovieCallBack() {
            @Override
            public void onMoviesLoaded(Movie movie)
            {
//                Log.d("MMMMMMMMMMMM",movie.getTitle());
                callback.onMoviesLoaded(movie);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable(); // do something
            }

            @Override
            public void onError(Throwable t) {
                callback.onError(t); // do something
            }
        });
    }
}
