package raider.project.EfreiCine.service;

import raider.project.EfreiCine.model.Movie;
import raider.project.EfreiCine.model.TheMovieDbAPI;

import java.io.IOException;
import java.util.List;

public interface MovieService {


    Movie findById(int movieId) throws IOException;

    Movie loadFromId(int movieId) throws IOException;

    Movie saveFromTmdb(int movieId) throws IOException;

    List<Movie> searchByTitle(String title) throws IOException;

}


