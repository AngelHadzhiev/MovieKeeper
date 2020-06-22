package cs.fhict.org.moviekeeper.data.remote;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import cs.fhict.org.moviekeeper.data.MovieDataSource;
import cs.fhict.org.moviekeeper.data.model.Movie;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRemoteDataSource  implements MovieDataSource {

    private static MovieRemoteDataSource INSTANCE;
    private ApiInterface service;

    public  MovieRemoteDataSource() {
        service = ApiClient.createService(ApiInterface.class);
    }
    @Override
    public void getMovies(String title, String apiKey, final LoadMovieCallBack callback) {
        Log.d("KEY",apiKey);
        service.getMoviesByTitle(title,apiKey).enqueue(new Callback<Movie>() {


            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {

                if (response.isSuccessful()) //isSuccessful()
                {

                    Log.d("RESPONSE ",response.toString());
                    Movie movies = response.body();// != null ? //response.body().getTitle() : null;
                    //   Log.d("MMMMM",movies.getTitle());
                    if (movies != null ) {
                        callback.onMoviesLoaded(movies);
                    } else {
                        callback.onDataNotAvailable();
                    }
                }
                else {
                    ResponseBody errorBody = response.errorBody();
                    Log.d("RESPONSE ERROR",errorBody.toString());
                }
                }


            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d("ERROR",t.getMessage());
                callback.onError(t); // make sure it sends t to repository
            }
        });
    }


    public static MovieRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MovieRemoteDataSource();
        }
        return INSTANCE;
    }
}
