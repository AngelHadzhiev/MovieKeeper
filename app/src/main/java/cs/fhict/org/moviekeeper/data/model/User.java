package cs.fhict.org.moviekeeper.data.model;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private String email;
    private String uid;
    private ArrayList<Movie> myMovies;

    public User(){

    }
    public User(String name, String email, String uid,ArrayList<Movie> movies ) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.myMovies = movies;
    }

    public ArrayList<Movie> getMyMovies() {
        return myMovies;
    }

    public void setMyMovies(ArrayList<Movie> myMovies) {
        this.myMovies = myMovies;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }
}
