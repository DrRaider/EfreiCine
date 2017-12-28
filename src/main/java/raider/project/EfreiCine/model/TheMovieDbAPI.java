package raider.project.EfreiCine.model;

import com.google.common.util.concurrent.RateLimiter;

import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.TmdbAuthenticator;
import com.uwetrottmann.tmdb2.TmdbInterceptor;

import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.BaseResultsPage;
import com.uwetrottmann.tmdb2.entities.CastMember;
import com.uwetrottmann.tmdb2.entities.CrewMember;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class TheMovieDbAPI {

    private static final boolean PRINT_REQUESTS = true;

    private static final String TEST_API_KEY = "df6b3a379fef6542e0fb5a102921a4d4";
    // limit requests for tests to avoid hitting TMDb rate limit (40 requests/10 seconds)
    private static final RateLimiter rateLimiter = RateLimiter.create(5);

    private static final Tmdb unauthenticatedInstance = new TheMovieDbAPI.TestTmdb(TEST_API_KEY);
    private static final Tmdb authenticatedInstance  = new TheMovieDbAPI.TestTmdb(TEST_API_KEY);

    private static Tmdb getUnauthenticatedInstance() {
        return unauthenticatedInstance;
    }

    public static BaseResultsPage<BaseMovie> searchMovie(String movieName) throws IOException {
        return TheMovieDbAPI.getUnauthenticatedInstance().searchService().movie(
                movieName,
                null,
                null,
                false,
                null,
                null,
                null
        ).execute().body();
    }

    public static Movie retrieveMovieData(int movieId) throws IOException {
        com.uwetrottmann.tmdb2.entities.Movie raw = TheMovieDbAPI
                .getUnauthenticatedInstance()
                .moviesService()
                .summary(movieId)
                .execute().body();

        com.uwetrottmann.tmdb2.entities.Credits credits = TheMovieDbAPI
                .getUnauthenticatedInstance()
                .moviesService()
                .credits(movieId)
                .execute().body();

        Movie movie = new Movie();
        assert raw != null;
        movie.setBackdropPath(raw.backdrop_path);
        movie.setBudget(raw.budget);

        StringBuilder cast = new StringBuilder();
        assert credits != null;
        for(CastMember ca : credits.cast) {
            if (ca.order == 1)
                cast.append(ca.name);
            if (ca.order == 2 || ca.order == 3)
                cast.append(", ").append(ca.name);
        }
        movie.setCast(cast.toString());

        StringBuilder director = new StringBuilder();
        StringBuilder producer = new StringBuilder();
        boolean cnt1 = false;
        boolean cnt2 = false;
        for(CrewMember cr : credits.crew) {
            if (Objects.equals(cr.job, "Director") && !cnt1) {
                director.append(cr.name);
                cnt1 = true;
            }
            if(Objects.equals(cr.job, "Director") && cnt1)
                director.append(", ").append(cr.name);

            if (Objects.equals(cr.job, "Producer") && !cnt2) {
                producer.append(cr.name);
                cnt2 = true;
            }
            if(Objects.equals(cr.job, "Producer") && cnt2)
                producer.append(", ").append(cr.name);
        }
        movie.setDirector(director.toString());
        movie.setProducer(producer.toString());

        movie.setTmdbId(raw.id);
        movie.setOriginalTitle(raw.original_title);
        movie.setOverview(raw.overview);
        movie.setPosterPath(raw.poster_path);
        System.out.println(raw.release_date);
        movie.setReleaseDate(raw.release_date);
        movie.setRuntime(raw.runtime);
        movie.setVoteAverage(raw.vote_average);
        movie.setVoteCount(raw.vote_count);

        return movie;
    }

    private static class TestTmdb extends Tmdb {
        TestTmdb(String apiKey) {
            super(apiKey);
        }

        @Override
        protected void setOkHttpClientDefaults(OkHttpClient.Builder builder) {
            final Tmdb instance = this;
            builder.authenticator(new TmdbAuthenticator(instance));
            builder.addInterceptor(chain -> {
                rateLimiter.acquire();
                return TmdbInterceptor.handleIntercept(chain, instance);
            });
            if (PRINT_REQUESTS) {
                // add logging
                // standard output is easier to read
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor(System.out::println);
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(logging);
            }
        }
    }
}
