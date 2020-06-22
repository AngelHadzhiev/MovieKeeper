package cs.fhict.org.moviekeeper.data.remote;

import java.util.ArrayList;
import java.util.List;

import cs.fhict.org.moviekeeper.data.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("?")
    Call<Movie> getMoviesByTitle(@Query("t") String title,@Query("apiKey") String apiKey);

}
