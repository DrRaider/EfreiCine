package raider.project.EfreiCine.service;

import raider.project.EfreiCine.model.Movie;
import raider.project.EfreiCine.model.TheMovieDbAPI;

import java.io.IOException;

public interface MovieService {

    public Movie addMovie(int movieId) throws IOException;

}


