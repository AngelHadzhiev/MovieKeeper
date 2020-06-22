package cs.fhict.org.moviekeeper.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ratings implements Serializable {
    @Expose
    @SerializedName("Source")
    private String source;
    @Expose
    @SerializedName("Value")
    private String value;

    public Ratings(){

    }
    public Ratings(String source, String value) {
        this.source = source;
        this.value = value;
    }

    public String GetSource(){
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
