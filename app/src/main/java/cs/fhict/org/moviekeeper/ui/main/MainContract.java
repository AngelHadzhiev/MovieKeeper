package cs.fhict.org.moviekeeper.ui.main;


import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


import androidx.fragment.app.FragmentManager;
import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.utils.mvp.IBaseFragment;
import cs.fhict.org.moviekeeper.utils.mvp.IBasePresenter;

interface MainContract {

    interface View  extends IBaseFragment{
        void loadStartingFragment();
        void finishActivity();
    }

    interface Presenter extends IBasePresenter<View>  {
        void onViewLoaded(FragmentManager fragmentManager);
        void onBackPressed();
        void onDashboardTabSelected(FragmentManager fragmentManager);
        void onLibraryTabSelected(FragmentManager fragmentManager);


    }


}
