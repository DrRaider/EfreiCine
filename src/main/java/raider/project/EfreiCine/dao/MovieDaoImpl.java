package raider.project.EfreiCine.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import raider.project.EfreiCine.model.Movie;

@Repository("userDao")
public class MovieDaoImpl extends AbstractDao<Integer, Movie> implements MovieDao {

    public void save(Movie movie) {
        persist(movie);
    }

    public Movie findById(int id) {
        return getByKey(id);
    }

    public Movie findByMovieId(String mId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("movieId", mId));
        return (Movie) crit.uniqueResult();
    }


}