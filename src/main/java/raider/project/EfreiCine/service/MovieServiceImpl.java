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
    MovieDao movieDao;


    public Movie findById(int movieId) throws IOException {
        return movieDao.findById(movieId);
    }

    public Movie loadFromId(int movieId) throws IOException {
        Movie m = findById(movieId);
        return m == null ? TheMovieDbAPI.retrieveMovieData(movieId) : m;
    }

    public Movie saveFromTmdb(int movieId) throws IOException {
        Movie tmp = movieDao.findByTmdbId(movieId);
        if(tmp != null) {
            return tmp;
        }

        Movie movie = TheMovieDbAPI.retrieveMovieData(movieId);
        movieDao.save(movie);
        return movie;
    }

    public List<Movie> searchByTitle(String title) throws IOException {
        return movieDao.findByTitleLike("%" + title + "%");
    }

}
