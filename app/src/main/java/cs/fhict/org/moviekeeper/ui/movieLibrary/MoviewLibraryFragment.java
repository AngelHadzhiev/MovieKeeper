package cs.fhict.org.moviekeeper.ui.movieLibrary;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Objects;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cs.fhict.org.moviekeeper.R;
import cs.fhict.org.moviekeeper.data.FirebaseRepository;
import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.data.remote.FirebaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviewLibraryFragment extends Fragment implements MovieLibraryContract.View {

    MovieLibraryPresenter movieLibraryPresenter;
    Toolbar toolbar;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    public MoviewLibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_moview_library, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = view.findViewById(R.id.toolbar_2);
        movieLibraryPresenter = new MovieLibraryPresenter(FirebaseRepository.getInstance(FirebaseHelper.getInstance()));
        movieLibraryPresenter.onViewActive(this);
        movieLibraryPresenter.onViewLoaded();

        movieLibraryPresenter.getMoviesPerUser(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        recyclerView = (RecyclerView) view.findViewById(R.id.movieLibraryAdapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public void onResume() {
        super.onResume();
        movieLibraryPresenter.getMoviesPerUser(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
    }

    @Override
    public void setTitleToolbar( int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle(resId);
            toolbar.setTitleTextAppearance(this.getContext(),R.style.RobotoTitleStyle);

        }
    }

    @Override
    public void updateViewData(ArrayList<Movie> movies) {
        // use a linear layout manager
        mAdapter = new MovieLibraryAdapter(movies,getActivity(),movieLibraryPresenter);
        recyclerView.setAdapter(mAdapter);
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
    }


    @Override
    public void ShowToast(String message) {
        Toast.makeText(this.getContext(),message,Toast.LENGTH_LONG).show();
    }
}
