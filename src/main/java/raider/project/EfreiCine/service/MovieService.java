package raider.project.EfreiCine.service;

import raider.project.EfreiCine.model.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieService {

    Movie save(int movieId) throws IOException;

    Movie findByTmdbId(int movieId);

    List<Movie> findAll();
}


