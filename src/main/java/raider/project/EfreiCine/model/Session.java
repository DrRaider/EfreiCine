package raider.project.EfreiCine.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="APP_SESSION")
public class Session {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name="SCREENING_ID", nullable = false)
    private int screeningId;

    @NotEmpty
    @Column(name = "DAY", nullable = false)
    private String day;

    @Temporal(TemporalType.TIME)
    @Column(name = "HOUR", nullable = false)
    private Date hour;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(int screeningId) {
        this.screeningId = screeningId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (id != session.id) return false;
        if (screeningId != session.screeningId) return false;
        return (day != null ? day.equals(session.day) : session.day == null) && (hour != null ? hour.equals(session.hour) : session.hour == null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + screeningId;
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (hour != null ? hour.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", screeningId=" + screeningId +
                ", day='" + day + '\'' +
                ", hour=" + hour +
                '}';
    }
}