package raider.project.EfreiCine.service;

import org.springframework.beans.factory.annotation.Autowired;
import raider.project.EfreiCine.dao.MovieDao;
import raider.project.EfreiCine.model.Movie;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;

import raider.project.EfreiCine.model.TheMovieDbAPI;

@Service("movieService")
@Transactional
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieDao movieDao;

    public Movie addMovie(int movieId) throws IOException {
        if(movieDao.exists(movieId)) {
            return movieDao.findById(movieId);
        }

        Movie movie = TheMovieDbAPI.retrieveMovieData(movieId);
        movieDao.save(movie);
        return movie;
    }

}
