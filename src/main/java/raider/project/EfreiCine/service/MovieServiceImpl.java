package raider.project.EfreiCine.service;

import org.springframework.beans.factory.annotation.Autowired;
import raider.project.EfreiCine.dao.MovieDao;
import raider.project.EfreiCine.model.Movie;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

import raider.project.EfreiCine.model.TheMovieDbAPI;

@Service("movieService")
@Transactional
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieDao dao;

    public Movie save(int movieId) throws IOException {
        Movie tmp = findByTmdbId(movieId);
        if(tmp != null) {
            return tmp;
        }

        Movie movie = TheMovieDbAPI.retrieveMovieData(movieId);
        dao.save(movie);
        return movie;
    }

    public Movie findByTmdbId(int movieId) {
        return dao.findByTmdbId(movieId);
    }

    public List<Movie> findAll() {
        return dao.findAll();
    }
}
