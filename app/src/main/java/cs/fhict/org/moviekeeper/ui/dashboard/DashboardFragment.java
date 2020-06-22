package cs.fhict.org.moviekeeper.ui.dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cs.fhict.org.moviekeeper.R;
import cs.fhict.org.moviekeeper.data.FirebaseRepository;
import cs.fhict.org.moviekeeper.data.MoviesRepository;
import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.data.model.Ratings;
import cs.fhict.org.moviekeeper.data.remote.FirebaseHelper;
import cs.fhict.org.moviekeeper.data.remote.MovieRemoteDataSource;
import cs.fhict.org.moviekeeper.ui.movieDetails.MovieDetailsActivity;
import cs.fhict.org.moviekeeper.ui.movieLibrary.MovieLibraryAdapter;
import cs.fhict.org.moviekeeper.utils.helper.CustomEditText;
import cs.fhict.org.moviekeeper.utils.helper.DrawableClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements DashboardContract.View{//, DrawableClickListener {


    private DashboardPresenter dashboardPresenter;
    private RecyclerView recyclerViewRecommendations;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView recyclerViewLastAdded;
    private RecyclerView.Adapter lastAddedAdapter;
    private RecyclerView.LayoutManager layoutManagerLastAdded;

    public DashboardFragment() {
        // Required empty public constructor


    }

    @Override
    public void onStart() {
        super.onStart();
        dashboardPresenter.onViewActive(this);
    }

    @Override
    public void onDestroy() {
        //add onInactive
        super.onDestroy();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewRecommendations = (RecyclerView) view.findViewById(R.id.recommendedRecyclerView);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        recyclerViewRecommendations.setLayoutManager(layoutManager);

        recyclerViewLastAdded = (RecyclerView) view.findViewById(R.id.lastAddedRecylerView);
        layoutManagerLastAdded = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        recyclerViewLastAdded.setLayoutManager(layoutManagerLastAdded);

        dashboardPresenter = new DashboardPresenter(MoviesRepository.getInstance(new MovieRemoteDataSource()), FirebaseRepository.getInstance(new FirebaseHelper()));
        dashboardPresenter.onViewActive(this);

        EditText editTextSearch =   view.findViewById(R.id.editTextSearch);
        editTextSearch.setOnTouchListener((v, event) -> {
            final int DRAWABLE_LEFT = 0;
            final int DRAWABLE_TOP = 1;
            final int DRAWABLE_RIGHT = 2;
            //  final int DRAWABLE_BOTTOM = 3;

            if(event.getAction() == MotionEvent.ACTION_UP) {
                if(event.getRawX() >= (editTextSearch.getRight() -editTextSearch.getLeft() - editTextSearch.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    // your action here
                    Log.d("CLICKED","CLICKED RIGHT");
                    Log.d("TEXTEDIT",editTextSearch.getText().toString());
                    dashboardPresenter.getMoviesByTitle(editTextSearch.getText().toString());
                    Log.d("TEXTEDIT",editTextSearch.getText().toString());
                    //return true;
                }
            }
            return false;
        });

        dashboardPresenter.getHighestRatingMovies(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        dashboardPresenter.getLastAddedMovies(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());





    }

    @Override
    public void ShowToast(String message) {

        Context context = getContext();
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, message,duration).show();
    }





    @Override
    public void updateRecyclerViewHighestRating(ArrayList<Movie> movies) {

        mAdapter = new DashboardRecommendationAdapter(movies,getContext());
        recyclerViewRecommendations.setAdapter(mAdapter);
        Objects.requireNonNull(recyclerViewRecommendations.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void updateRecyclerViewLastAdded(ArrayList<Movie> movies) {
        lastAddedAdapter = new DashboardLastAddedAdapter(movies,getContext());
        recyclerViewLastAdded.setAdapter(lastAddedAdapter);
        Objects.requireNonNull(recyclerViewLastAdded.getAdapter()).notifyDataSetChanged();
    }





    @Override
    public void goToMovieDetails(Movie movies) {


        ShowToast("LANG" + movies.getLanguage());
        Intent intent = new Intent(getContext(), MovieDetailsActivity.class );
        intent.putExtra("title",movies.getTitle());
        startActivity(intent);
    }


}
