package raider.project.EfreiCine.model;

import com.google.common.util.concurrent.RateLimiter;

import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.TmdbAuthenticator;
import com.uwetrottmann.tmdb2.TmdbInterceptor;

import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.BaseResultsPage;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
public class TheMovieDbAPI {

    private static final boolean PRINT_REQUESTS = true;

    private static final String TEST_API_KEY = "df6b3a379fef6542e0fb5a102921a4d4";
    // limit requests for tests to avoid hitting TMDb rate limit (40 requests/10 seconds)
    private static final RateLimiter rateLimiter = RateLimiter.create(5);

    private static final Tmdb unauthenticatedInstance = new TheMovieDbAPI.TestTmdb(TEST_API_KEY);
    private static final Tmdb authenticatedInstance  = new TheMovieDbAPI.TestTmdb(TEST_API_KEY);

    public static Tmdb getUnauthenticatedInstance() {
        return unauthenticatedInstance;
    }

    protected static Tmdb getAuthenticatedInstance() {
        return authenticatedInstance;
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

        Movie movie = new Movie();
        movie.setBackdropPath(raw.backdrop_path);
        movie.setBudget(raw.budget);
        //TODO: movie.setCast();
        //TODO: movie.setDirector();
        movie.setId(raw.id);
        movie.setOriginalTitle(raw.original_title);
        movie.setOverview(raw.overview);
        movie.setPosterPath(raw.poster_path);
        //TODO: movie.setProducer();
        movie.setReleaseDate(raw.release_date);
        movie.setRuntime(raw.runtime);
        movie.setVoteAverage(raw.vote_average);
        movie.setVoteCount(raw.vote_count);

        return movie;
    }

    private static class TestTmdb extends Tmdb {
        public TestTmdb(String apiKey) {
            super(apiKey);
        }

        @Override
        protected void setOkHttpClientDefaults(OkHttpClient.Builder builder) {
            final Tmdb instance = this;
            builder.authenticator(new TmdbAuthenticator(instance));
            builder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    rateLimiter.acquire();
                    return TmdbInterceptor.handleIntercept(chain, instance);
                }
            });
            if (PRINT_REQUESTS) {
                // add logging
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String s) {
                        // standard output is easier to read
                        System.out.println(s);

                    }
                }); 
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(logging);
            }
        }
    }
}
