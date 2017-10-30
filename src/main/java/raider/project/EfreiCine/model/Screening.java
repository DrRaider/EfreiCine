package raider.project.EfreiCine.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="APP_SCREENING")
public class Screening {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name="MOVIE_ID", nullable = false)
    private int movieId;

    @NotEmpty
    @Column(name = "THEATER_ID", nullable = false)
    private int theaterId;

    @NotEmpty
    @Column(name = "START_DATE", nullable = false)
    private String startDate;

    @NotEmpty
    @Column(name = "END_DATE", nullable = false)
    private String endDate;

    @NotEmpty
    @Column(name = "DAYS", nullable = false)
    private String days;

    @NotEmpty
    @Column(name = "AGE_LIMIT", nullable = false)
    private int ageLimit;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Screening screening = (Screening) o;

        return id == screening.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", theaterId=" + theaterId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", days='" + days + '\'' +
                ", ageLimit='" + ageLimit + '\'' +
                '}';
    }
}