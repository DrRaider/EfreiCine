package raider.project.EfreiCine.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import raider.project.EfreiCine.model.Movie;

import java.util.List;

@Repository("movieDao")
public class MovieDao extends AbstractDao<Movie> {

    public Movie findByTmdbId(int tmdbId) {
        return (Movie) createEntityCriteria()
                .add(Restrictions.eq("tmdbId", tmdbId))
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Movie> findAll(){
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("originalTitle"));
        return (List<Movie>)crit.list();
    }

}
