package cs.fhict.org.moviekeeper.ui.movieDetails;

import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.utils.mvp.IBaseActivity;
import cs.fhict.org.moviekeeper.utils.mvp.IBasePresenter;

public interface MovieDetailsContract {

    interface View extends IBaseActivity{
        void updateView(Movie movies);
        void addMovieToLibrary(Movie movie);
    }

    interface  Presenter extends IBasePresenter<View> {
        void getMoviesByTitle(String title);
        void onAddMovieToLibrary(Movie movie);
    }
}
