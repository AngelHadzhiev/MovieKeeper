package cs.fhict.org.moviekeeper.ui.movieLibrary;

import android.os.Build;
import android.view.View;

import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.utils.mvp.IBaseFragment;
import cs.fhict.org.moviekeeper.utils.mvp.IBasePresenter;;

interface  MovieLibraryContract {

    interface View extends IBaseFragment {
        void setTitleToolbar( int resId);
        void updateViewData(ArrayList<Movie> movies);
    }

    interface Presenter extends IBasePresenter<View> {

        void onViewLoaded();
        void getMoviesPerUser(String uid);
        void onDeleteMovie(Movie movie);


    }


}
