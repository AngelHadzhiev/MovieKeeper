package cs.fhict.org.moviekeeper.ui.dashboard;

import android.icu.text.NumberFormat;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import cs.fhict.org.moviekeeper.data.FirebaseDataSource;
import cs.fhict.org.moviekeeper.data.FirebaseRepository;
import cs.fhict.org.moviekeeper.data.MovieDataSource;
import cs.fhict.org.moviekeeper.data.MoviesRepository;
import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.data.model.User;
import cs.fhict.org.moviekeeper.utils.constant.Constant;
import cs.fhict.org.moviekeeper.utils.mvp.IBasePresenter;

public class DashboardPresenter implements  DashboardContract.Presenter, IBasePresenter<DashboardContract.View> {

    private final ArrayList<Movie> movieArrayList;
    private DashboardContract.View view;
    private final MoviesRepository moviesRepository;
    private final FirebaseRepository firebaseRepository;

    public DashboardPresenter(MoviesRepository moviesRepository,FirebaseRepository firebaseRepository) {
        this.moviesRepository = moviesRepository;
        this.firebaseRepository = firebaseRepository;
        movieArrayList = new ArrayList<>();

    }



    @Override
    public void onViewActive(DashboardContract.View view) {
        this.view = view;
    }

    @Override
    public void onViewInactive() {
        this.view = null;
    }


    @Override
    public void getMoviesByTitle(final String title) {
        moviesRepository.getMovies(title, Constant.apiKey, new MovieDataSource.LoadMovieCallBack() {
            @Override
            public void onMoviesLoaded(Movie movies) {

                view.goToMovieDetails(movies);
                view.ShowToast(movies.getTitle() + movies.getActors());


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
    public void getHighestRatingMovies(String uid) {
        firebaseRepository.getHighestRatingMovies(uid, new FirebaseDataSource.FirebaseGetMoviesListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(User user) {
                for (Movie movie: user.getMyMovies()) {

                    String ratingString =movie.getRatings().get(0).getValue();

                    Log.d("RATING",ratingString);
                    int ratingToInt = 0;
                    try {
                        ratingToInt = NumberFormat.getInstance().parse(ratingString).intValue();
                        Log.d("RATING",String.valueOf(ratingToInt));
                        if (ratingToInt>=7) {
                            movieArrayList.add(movie);
                            view.updateRecyclerViewHighestRating(movieArrayList);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }



                }
            }

            @Override
            public void onFailure(String message) {
                view.ShowToast(message);
            }
        });
    }

    @Override
    public void getLastAddedMovies(String uid) {
        firebaseRepository.getHighestRatingMovies(uid, new FirebaseDataSource.FirebaseGetMoviesListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(User user) {
                ArrayList<Movie> lastAddedMovies = new ArrayList<>();

                lastAddedMovies.addAll(user.getMyMovies().subList( user.getMyMovies().size()-Math.min( user.getMyMovies().size(),5), user.getMyMovies().size()));
                view.updateRecyclerViewLastAdded(lastAddedMovies);
//
            }

            @Override
            public void onFailure(String message) {
                view.ShowToast(message);
            }
        });
    }




}
