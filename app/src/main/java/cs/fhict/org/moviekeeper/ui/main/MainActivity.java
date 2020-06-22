package cs.fhict.org.moviekeeper.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import cs.fhict.org.moviekeeper.R;
import cs.fhict.org.moviekeeper.data.MoviesRepository;
import cs.fhict.org.moviekeeper.data.remote.MovieRemoteDataSource;
import cs.fhict.org.moviekeeper.ui.dashboard.DashboardFragment;
import cs.fhict.org.moviekeeper.ui.movieLibrary.MoviewLibraryFragment;
import cs.fhict.org.moviekeeper.utils.helper.CustomEditText;
import cs.fhict.org.moviekeeper.utils.helper.DrawableClickListener;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener {

    private MainPresenter mainPresenter;
    private BottomNavigationView navigation;
    private FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.nav_bottom_bar);
        fragmentManager = getSupportFragmentManager();
        mOnNavigationItemSelectedListener = this;
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        this.mainPresenter = new MainPresenter(MoviesRepository.getInstance(new MovieRemoteDataSource()));

        mainPresenter.onViewLoaded(fragmentManager);
//        initializeBottomNav();
//        initializeStartingFragment();

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        mainPresenter.onViewLoaded(fragmentManager);
    }

    @Override
    public void loadStartingFragment() {
        Fragment fragment = new DashboardFragment();
        fragmentManager.findFragmentById(R.id.dashboard);
        if (fragment != null) {
            fragmentManager.beginTransaction().disallowAddToBackStack()
                    //.setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .replace(R.id.container, fragment).commit();
        }

    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.onViewActive(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mainPresenter.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.dashboard) {

            mainPresenter.onDashboardTabSelected(fragmentManager);

//            FragmentManager fragmentManager = getSupportFragmentManager();
//            Fragment fragment = new DashboardFragment();
//            fragment = new DashboardFragment();
//            fragmentManager.findFragmentById(R.id.dashboard);
//            if (fragment != null) {
//                fragmentManager.beginTransaction().disallowAddToBackStack()
//                        //.setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
//                        .replace(R.id.container, fragment).commit();
//            }
        } else if (item.getItemId() == R.id.movieLibrary) {
                mainPresenter.onLibraryTabSelected(fragmentManager);
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            Fragment fragment = new MoviewLibraryFragment();
//            fragment = new MoviewLibraryFragment();
//            fragmentManager.findFragmentById(R.id.movieLibrary);
//            if (fragment != null) {
//                fragmentManager.beginTransaction().disallowAddToBackStack()
//                        //.setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
//                        .replace(R.id.container, fragment).commit();
//            }

        }
        return false;
    }



    @Override
    public void ShowToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }


}