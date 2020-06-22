package cs.fhict.org.moviekeeper.ui.dashboard;

import android.view.View;

import java.util.ArrayList;

import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.utils.mvp.IBaseFragment;

interface DashboardContract {

    interface View extends IBaseFragment {
        void updateRecyclerViewHighestRating(ArrayList<Movie> movies);
        void updateRecyclerViewLastAdded(ArrayList<Movie> movies);

        void goToMovieDetails(Movie movies);
    }

    interface Presenter {

        void getMoviesByTitle(String title);
        void getHighestRatingMovies(String uid);
        void getLastAddedMovies(String uid);



    }


}
