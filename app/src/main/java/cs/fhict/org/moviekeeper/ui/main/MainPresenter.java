package cs.fhict.org.moviekeeper.ui.main;

import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import cs.fhict.org.moviekeeper.R;
import cs.fhict.org.moviekeeper.data.MovieDataSource;
import cs.fhict.org.moviekeeper.data.MoviesRepository;
import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.ui.dashboard.DashboardFragment;
import cs.fhict.org.moviekeeper.ui.movieLibrary.MoviewLibraryFragment;
import cs.fhict.org.moviekeeper.utils.constant.Constant;

public class MainPresenter implements MainContract.Presenter {

    private final MoviesRepository moviesRepository;
    private  MainContract.View view ;
    BottomNavigationView navigation;

    public MainPresenter(MoviesRepository moviesRepository) {

        this.moviesRepository = moviesRepository;
    }


    @Override
    public void onViewLoaded(FragmentManager fragmentManager) {
       // view.initializeStartingFragment();
        //view.loadStartingFragment(fragmentManager);
        Fragment fragment = new DashboardFragment();
        fragmentManager.findFragmentById(R.id.dashboard);
        if (fragment != null) {
            fragmentManager.beginTransaction().disallowAddToBackStack()
                    //.setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .replace(R.id.container, fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        view.finishActivity();
    }

    @Override
    public void onDashboardTabSelected(FragmentManager fragmentManager) {
        Fragment fragment = new DashboardFragment();
        fragmentManager.findFragmentById(R.id.dashboard);
        if (fragment != null) {
            fragmentManager.beginTransaction().disallowAddToBackStack()
                    //.setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .replace(R.id.container, fragment).commit();
        }
    }

    @Override
    public void onLibraryTabSelected(FragmentManager fragmentManager) {
        Fragment fragment = new MoviewLibraryFragment();

        fragmentManager.findFragmentById(R.id.movieLibrary);
        if (fragment != null) {
            fragmentManager.beginTransaction().disallowAddToBackStack()
                    //.setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .replace(R.id.container, fragment).commit();
        }
    }


    @Override
    public void onViewActive(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void onViewInactive() {
        this.view = null;
    }






}
