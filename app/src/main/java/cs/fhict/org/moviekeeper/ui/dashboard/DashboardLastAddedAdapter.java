package cs.fhict.org.moviekeeper.ui.dashboard;

import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cs.fhict.org.moviekeeper.R;
import cs.fhict.org.moviekeeper.data.model.Movie;
import cs.fhict.org.moviekeeper.data.model.Ratings;

public class DashboardLastAddedAdapter extends RecyclerView.Adapter<DashboardLastAddedAdapter.DashboardViewHodlerLastAdded> {



    private ArrayList<Movie> movies;
    private Context context;

    public DashboardLastAddedAdapter( ArrayList<Movie> movies,Context context) {
        this.movies = movies;
        this.context = context;

    }
    @NonNull
    @Override
    public DashboardLastAddedAdapter.DashboardViewHodlerLastAdded onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_list, parent, false); // maybe one list is enough for both
        return new DashboardViewHodlerLastAdded(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardLastAddedAdapter.DashboardViewHodlerLastAdded holder, int position) {
        Movie movie = movies.get(position);



        holder.tvRating.setText( movie.getRatings().get(0).getValue());

        holder.tvTitle.setText(movie.getTitle());
        Glide
                .with(context)
                .load(movie.getPoster())
                .centerCrop()
                .into(holder.imageViewPosterRecommended);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class DashboardViewHodlerLastAdded extends RecyclerView.ViewHolder implements View.OnClickListener  {

        TextView tvTitle = itemView.findViewById(R.id.tvTitle);
        TextView tvRating = itemView.findViewById(R.id.textViewRatingRecommended);
        ImageView imageViewPosterRecommended = itemView.findViewById(R.id.imageViewPosterRecommended);


        public DashboardViewHodlerLastAdded(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

        }
    }
}