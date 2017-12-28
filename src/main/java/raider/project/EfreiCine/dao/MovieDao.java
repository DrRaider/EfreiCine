package raider.project.EfreiCine.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import raider.project.EfreiCine.model.Movie;

@Repository("movieDao")
public class MovieDao extends AbstractDao<Movie> {

    public Movie findByTmdbId(int tmdbId) {
        return (Movie) createEntityCriteria()
                .add(Restrictions.eq("tmdbId", tmdbId))
                .uniqueResult();
    }
}
