package raider.project.EfreiCine.service;

import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.BaseResultsPage;

import java.io.IOException;

public interface MovieService {

    public String getMovieName();

    public void setMovieName(String movieName);

    public BaseResultsPage<BaseMovie> movieSearch() throws IOException;

}


