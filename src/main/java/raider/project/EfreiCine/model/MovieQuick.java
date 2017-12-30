package raider.project.EfreiCine.model;

import com.uwetrottmann.tmdb2.entities.BaseMovie;

import java.util.Date;

public class MovieQuick {
    public int id;
    public boolean local;
    public String originalTitle;
    public String overview;
    public String posterPath;
    public Date releaseDate;

    public static MovieQuick from(Movie movie) {
        MovieQuick quick = new MovieQuick();
        quick.local = true;
        quick.id = movie.getId();
        quick.originalTitle = movie.getOriginalTitle();
        quick.overview = movie.getOverview();
        quick.releaseDate = movie.getReleaseDate();
        quick.posterPath = Movie.IMG_PATH_PREFIX + movie.getPosterPath();

        return quick;
    }

    public static MovieQuick from(BaseMovie movie) {
        MovieQuick quick = new MovieQuick();
        quick.local = false;
        quick.id = movie.id;
        quick.originalTitle = movie.original_title;
        quick.overview = movie.overview;
        quick.releaseDate = movie.release_date;
        quick.posterPath = Movie.IMG_PATH_PREFIX + movie.poster_path;

        return quick;
    }

}
