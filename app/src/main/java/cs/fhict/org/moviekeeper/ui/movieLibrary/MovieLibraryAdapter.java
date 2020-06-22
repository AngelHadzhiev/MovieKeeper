package cs.fhict.org.moviekeeper.ui.movieLibrary;

import android.content.Context;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;

import java.text.ParseException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import cs.fhict.org.moviekeeper.R;
import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.data.model.Ratings;
import cs.fhict.org.moviekeeper.ui.movieDetails.MovieDetailsActivity;


public class MovieLibraryAdapter  extends RecyclerView.Adapter<MovieLibraryAdapter.MoviesViewHolder> {

    ArrayList<Movie> movies;
    Context context;
    MovieLibraryPresenter presenter;

    public MovieLibraryAdapter(ArrayList<Movie> movies,Context context,MovieLibraryPresenter presenter) {
        this.movies = movies;
        this.context =context;
        this.presenter = presenter;
    }
    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.library_list, parent, false);
        return new MoviesViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {

        Movie movie = movies.get(position);

        Glide
                .with(context)
                .load(movie.getPoster())
                .centerCrop()
                .into(holder.moviePoster);
        holder.movieTitle.setText(movie.getTitle());
        holder.directorName.setText(movie.getDirectors());
        holder.actorName.setText(movie.getActors());
        for (Ratings rating : movie.getRatings())
        {
            holder.rating.setText(rating.getValue().toString());
            String s=rating.getValue();
            try {
                int i= NumberFormat.getInstance().parse(s).intValue();
                holder.progressBarRating.setProgress(i);
                System.out.println("VALUE: " + i );
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"REMOVE " + movie.getTitle(), Toast.LENGTH_LONG).show();

                if (presenter!=null) {
                    presenter.onDeleteMovie(movie);
                    Toast.makeText(view.getContext(),"REMOVED", Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(view.getContext(),"CANT REMOVE " + movie.getTitle(), Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return (movies == null) ? 0 : movies.size();
    }

    public static class MoviesViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView movieTitle,actorName,directorName,rating;
        public ImageView moviePoster;
        public ProgressBar progressBarRating;
        public Button btnRemove;

        public MoviesViewHolder(View v) {
            super(v);
            movieTitle = v.findViewById(R.id.textViewTitle);
            directorName = v.findViewById(R.id.textViewDirector);
            rating = v.findViewById(R.id.textViewRatings);
            moviePoster = v.findViewById(R.id.imageViewPoster);
            actorName = v.findViewById(R.id.textViewActor);
            progressBarRating = v.findViewById(R.id.progressBarRating);
            btnRemove = v.findViewById(R.id.btnRemove);
        }

    }


}
