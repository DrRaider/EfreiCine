package raider.project.EfreiCine.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import raider.project.EfreiCine.model.Movie;
import raider.project.EfreiCine.model.Screening;

import java.util.List;

@Repository("screeningDao")
public class ScreeningDao extends AbstractDao<Screening> {

    @SuppressWarnings("unchecked")
    public List<Screening> findByTheater(int tId) {
        return (List<Screening>) createEntityCriteria()
                .add(Restrictions.eq("theaterId", tId))
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<Screening> findByMovie(int mId) {
        return (List<Screening>) createEntityCriteria()
                .add(Restrictions.eq("movieId", mId))
                .list();
    }

    public Screening findByTheaterAndMovie(int tId, int mId) {
        return (Screening) createEntityCriteria()
                .add(Restrictions.eq("theaterId", tId))
                .add(Restrictions.eq("movieId", mId))
                .uniqueResult();
    }


}
