package raider.project.EfreiCine.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name="APP_USER_THEATER")
public class UserTheater {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "USER_ID", nullable = false)
    private int userId;

    @NotEmpty
    @Column(name = "THEATER_ID", nullable = false)
    private int theaterId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTheater that = (UserTheater) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        return theaterId == that.theaterId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + theaterId;
        return result;
    }

    @Override
    public String toString() {
        return "UserTheater{" +
                "id=" + id +
                ", userId=" + userId +
                ", theaterId=" + theaterId +
                '}';
    }
}