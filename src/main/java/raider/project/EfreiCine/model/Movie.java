package raider.project.EfreiCine.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;


import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="APP_MOVIE")
public class Movie {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name="MOVIE_ID", nullable = false)
    private Integer movieId;

    @NotEmpty
    @Column(name="ORIGINAL_TITLE", nullable = false)
    private String originalTitle;

    @NotEmpty
    @Column(name="BACKDROP_PATH", nullable = false)
    private String backdropPath;

    @NotEmpty
    @Column(name = "OVERVIEW", nullable = false)
    private String overview;

    @NotEmpty
    @Column(name = "RELEASE_DATE", nullable = false)
    private String releaseDate;

    @NotEmpty
    @Column(name = "BUDGET", nullable = false)
    private Integer budget;

    @NotEmpty
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

    @NotEmpty
    @Column(name = "VOTE_AVERAGE", nullable = false)
    private Double voteAverage;

    @NotEmpty
    @Column(name = "VOTE_COUNT", nullable = false)
    private Integer voteCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return movieId.equals(movie.movieId);
    }

    @Override
    public int hashCode() {
        return movieId.hashCode();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", originalTitle='" + originalTitle + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", budget=" + budget +
                ", runtime=" + runtime +
                ", cast='" + cast + '\'' +
                ", director='" + director + '\'' +
                ", producer='" + producer + '\'' +
                ", voteAverage='" + voteAverage + '\'' +
                ", voteCount='" + voteCount + '\'' +
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