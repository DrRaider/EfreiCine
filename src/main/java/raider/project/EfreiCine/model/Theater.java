package raider.project.EfreiCine.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="APP_THEATER")
public class Theater {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name="NUMBER", nullable = false)
    private String number;

    @NotEmpty
    @Column(name = "STREET", nullable = false)
    private String street;

    @NotEmpty
    @Column(name = "CITY", nullable = false)
    private String city;

    @OneToMany(mappedBy = "theater", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserTheater> userTheater = new HashSet<UserTheater>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<UserTheater> getUserTheater() {
        return userTheater;
    }

    public void setUserTheaters(Set<UserTheater> userTheater) {
        this.userTheater = userTheater;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Theater theater = (Theater) o;

        if (id != theater.id) return false;
        if (number != null ? !number.equals(theater.number) : theater.number != null) return false;
        if (street != null ? !street.equals(theater.street) : theater.street != null) return false;
        if (city != null ? !city.equals(theater.city) : theater.city != null) return false;
        return userTheater != null ? userTheater.equals(theater.userTheater) : theater.userTheater == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Theater{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}