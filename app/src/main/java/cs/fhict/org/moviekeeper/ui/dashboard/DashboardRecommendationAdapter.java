package cs.fhict.org.moviekeeper.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cs.fhict.org.moviekeeper.R;
import cs.fhict.org.moviekeeper.data.model.Movie;

public class DashboardRecommendationAdapter extends RecyclerView.Adapter<DashboardRecommendationAdapter.DashboardViewHodler> {

    //private DashboardPresenter dashboardPresenter;
    private ArrayList<Movie> movies;
    private Context context;
    public DashboardRecommendationAdapter( ArrayList<Movie> movies,Context context) {
        this.movies = movies;
        this.context = context;

    }
    @NonNull
    @Override
    public DashboardViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_list, parent, false); // maybe one list is enough for both
        return new DashboardViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHodler holder, int position) {
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

    public class DashboardViewHodler extends RecyclerView.ViewHolder {

        TextView tvRating = itemView.findViewById(R.id.textViewRatingRecommended);
        TextView tvTitle = itemView.findViewById(R.id.tvTitle);
        ImageView imageViewPosterRecommended = itemView.findViewById(R.id.imageViewPosterRecommended);

        public DashboardViewHodler(@NonNull View itemView) {
            super(itemView);
        }


    }
}
