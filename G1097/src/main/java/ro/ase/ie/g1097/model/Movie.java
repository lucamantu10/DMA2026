package ro.ase.ie.g1097.model;

import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable {
    private String title;
    private Date releaseDate;

    public Movie(Date releaseDate, String title) {
        this.releaseDate = releaseDate;
        this.title = title;
    }
}
