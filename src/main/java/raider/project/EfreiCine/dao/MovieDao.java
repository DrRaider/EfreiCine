package raider.project.EfreiCine.dao;

import raider.project.EfreiCine.model.Movie;

public interface MovieDao {

    void save(Movie movie);

    Movie findById(int id);

    Movie findByMovieId(String mId);

}
