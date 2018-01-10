package raider.project.EfreiCine.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="APP_USER_THEATER")
public class UserTheater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTheater that = (UserTheater) o;

        if (id != that.id) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return theater != null ? theater.equals(that.theater) : that.theater == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (theater != null ? theater.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserTheater{" +
                "id=" + id +
                ", user=" + user +
                ", theater=" + theater +
                '}';
    }
}