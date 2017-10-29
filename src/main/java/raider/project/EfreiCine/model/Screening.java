package raider.project.EfreiCine.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.uwetrottmann.tmdb2.entities.BaseMovie;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="APP_SCREENING")
public class Screening {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name="NUMBER", nullable = false)
    private int number;

    @NotEmpty
    @Column(name = "STREET", nullable = false)
    private String street;

    @NotEmpty
    @Column(name = "CITY", nullable = false)
    private String city;

}