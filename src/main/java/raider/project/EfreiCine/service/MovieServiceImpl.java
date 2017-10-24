package raider.project.EfreiCine.service;

import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import retrofit2.Call;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;

import raider.project.EfreiCine.model.TheMovieDbAPI;

@Service
@Transactional
public class MovieServiceImpl implements MovieService{

    private String movieName;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void movieSearch() throws IOException {
        Call<MovieResultsPage> call = TheMovieDbAPI.getUnauthenticatedInstance().searchService().movie(
                getMovieName(),
                null,
                null,
                false,
                null,
                null,
                null
        );

        MovieResultsPage movieResults = call.execute().body();
    }

}