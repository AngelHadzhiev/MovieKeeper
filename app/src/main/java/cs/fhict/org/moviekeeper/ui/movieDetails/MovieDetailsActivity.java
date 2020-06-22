package cs.fhict.org.moviekeeper.ui.movieDetails;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import cs.fhict.org.moviekeeper.R;
import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.data.model.Ratings;

import android.icu.text.NumberFormat;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsContract.View{

    MovieDetailsPresenter presenter;
    ImageView imageViewPoster;
    TextView textViewTitle,textViewActor, textViewDirector,textViewPlot,textViewRating;
    ProgressBar progressBarRating;
    Button btnAddMovie ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        presenter = new MovieDetailsPresenter();
        presenter.onViewActive(this);

        String extraTitle = getIntent().getStringExtra("title");

        presenter.getMoviesByTitle(extraTitle);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void updateView(Movie movies) {

        textViewTitle = findViewById(R.id.textViewTitleDetails);
        textViewActor = findViewById(R.id.textViewActorsDetaisl);
        textViewDirector = findViewById(R.id.textViewDirectorDetails);
        textViewPlot = findViewById(R.id.textViewPlotDetails);
        progressBarRating = findViewById(R.id.progressBarDetails);
        imageViewPoster = findViewById(R.id.imageViewPosterDetails);
        textViewRating = findViewById(R.id.textViewRatingDetails);


        textViewTitle.setText(movies.getTitle());
        textViewActor.setText(movies.getActors());
        textViewDirector.setText(movies.getDirectors());
        textViewPlot.setText(movies.getPlot());

        if (movies.getRatings()!=null) {
            for (Ratings rating : movies.getRatings()) {

                String s = rating.getValue();
                try {
                    int i = NumberFormat.getInstance().parse(s).intValue();

                    progressBarRating.setProgress(i);
                    if (s != null) {
                        textViewRating.setText(s);
                    } else {
                        textViewRating.setText("No Rating");
                    }

                    System.out.println("VALUE: " + i);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }

        Glide
                .with(this)
                .load(movies.getPoster())
                .centerCrop()
                .placeholder(R.drawable.ic_movies)
                .into(imageViewPoster);



    }

    @Override
    public void addMovieToLibrary(Movie movie) {

        btnAddMovie = findViewById(R.id.btnAddBookToLibrary);

        btnAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAddMovieToLibrary(movie);
                MovieDetailsActivity.super.onBackPressed();
            }
        });

    }

    @Override
    public void ShowToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}