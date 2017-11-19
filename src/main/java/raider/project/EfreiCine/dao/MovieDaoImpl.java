package raider.project.EfreiCine.dao;

import org.springframework.stereotype.Repository;

import raider.project.EfreiCine.model.Movie;

@Repository("movieDao")
public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {

    public void save(Movie movie) {
        persist(movie);
    }

    public Movie findById(int id) {
        return getByKey(id);
    }

    public boolean exists(int id) {
        return super.exists(id);
    }
}