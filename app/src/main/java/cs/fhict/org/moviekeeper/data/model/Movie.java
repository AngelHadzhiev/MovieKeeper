package cs.fhict.org.moviekeeper.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.room.Entity;


@Entity
public class Movie implements Serializable {

    @Expose
    @SerializedName("Title")
    private String title;
    @Expose
    @SerializedName("Year")
    private String year;
    @Expose
    @SerializedName("Rated")
    private String rated;
    @Expose
    @SerializedName("Released")
    private String released;
    @Expose
    @SerializedName("Runtime")
    private String runtime;
    @Expose
    @SerializedName("Genre")
    private String genres;
    @Expose
    @SerializedName("Director")
    private String directors;
    @Expose
    @SerializedName("Actors")
    private String actors;
    @Expose
    @SerializedName("Plot")
    private String plot;
    @Expose
    @SerializedName("Language")
    private String language;
    @Expose
    @SerializedName("Awards")
    private String awards;
    @Expose
    @SerializedName("Poster")
    private String poster;
    @Expose
    @SerializedName("Ratings")
    private ArrayList<Ratings> ratings;


    public Movie(){

    }
    public Movie(String title, String year, String genres) {
        this.title = title;
        this.year= year;
        this.genres = genres;
    }

    public String getTitle () {
        return this.title;
    }

    public ArrayList<Ratings> getRatings() {
        return ratings;
    }

    public String getActors() {
        return actors;
    }

    public String getDirectors() {
        return directors;
    }

    public String getYear() {
        return year;
    }

    public String getAwards() {
        return awards;
    }

    public String getLanguage() {
        return language;
    }

    public String getPlot() {
        return plot;
    }

    public String getPoster() {
        return poster;
    }

    public String getRated() {
        return rated;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }


    public String getGenres () {
        return this.genres;
    }


}
