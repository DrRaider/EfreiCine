package raider.project.EfreiCine.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name="APP_MOVIE")
public class Movie {

    public static final String IMG_PATH_PREFIX = "http://image.tmdb.org/t/p/w500";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "TMDB_ID", unique=true, nullable = false)
    private int tmdbId;

    @NotEmpty
    @Column(name = "ORIGINAL_TITLE", nullable = false)
    private String originalTitle;

    @NotEmpty
    @Column(name = "BACKDROP_PATH", nullable = false)
    private String backdropPath;

    @NotEmpty
    @Column(name = "POSTER_PATH", nullable = false)
    private String posterPath;

    @NotEmpty
    @Column(name = "OVERVIEW", nullable = false)
    private String overview;

    @Temporal(TemporalType.DATE)
    @Column(name = "RELEASE_DATE", nullable = false)
    private Date releaseDate;

    @NotNull
    @Column(name = "BUDGET", nullable = false)
    private Integer budget;

    @NotNull
    @Column(name = "RUNTIME", nullable = false)
    private Integer runtime;

    @NotEmpty
    @Column(name = "CAST", nullable = false)
    private String cast;

    @NotEmpty
    @Column(name = "DIRECTOR", nullable = false)
    private String director;

    @NotEmpty
    @Column(name = "PRODUCER", nullable = false)
    private String producer;

    @NotNull
    @Column(name = "VOTE_AVERAGE", nullable = false)
    private Double voteAverage;

    @NotNull
    @Column(name = "VOTE_COUNT", nullable = false)
    private Integer voteCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(int tmdbId) {
        this.tmdbId = tmdbId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (tmdbId != movie.tmdbId) return false;
        if (!originalTitle.equals(movie.originalTitle)) return false;
        if (!backdropPath.equals(movie.backdropPath)) return false;
        if (!posterPath.equals(movie.posterPath)) return false;
        if (!overview.equals(movie.overview)) return false;
        if (!releaseDate.equals(movie.releaseDate)) return false;
        if (!budget.equals(movie.budget)) return false;
        if (!runtime.equals(movie.runtime)) return false;
        if (!cast.equals(movie.cast)) return false;
        if (!director.equals(movie.director)) return false;
        if (!producer.equals(movie.producer)) return false;
        if (!voteAverage.equals(movie.voteAverage)) return false;
        return voteCount.equals(movie.voteCount);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + tmdbId;
        result = 31 * result + originalTitle.hashCode();
        result = 31 * result + backdropPath.hashCode();
        result = 31 * result + posterPath.hashCode();
        result = 31 * result + overview.hashCode();
        result = 31 * result + releaseDate.hashCode();
        result = 31 * result + budget.hashCode();
        result = 31 * result + runtime.hashCode();
        result = 31 * result + cast.hashCode();
        result = 31 * result + director.hashCode();
        result = 31 * result + producer.hashCode();
        result = 31 * result + voteAverage.hashCode();
        result = 31 * result + voteCount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", tmdbId=" + tmdbId +
                ", originalTitle='" + originalTitle + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate=" + releaseDate +
                ", budget=" + budget +
                ", runtime=" + runtime +
                ", cast='" + cast + '\'' +
                ", director='" + director + '\'' +
                ", producer='" + producer + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                '}';
    }
}
/*
    @GET("movie/{movie_id}")
    Call<Movie> summary(
            @Path("movie_id") int movieId,
            @Query("append_to_response") AppendToResponse appendToResponse
    );
 */