package raider.project.EfreiCine.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import raider.project.EfreiCine.model.Screening;

@Repository("screeningDao")
public class ScreeningDao extends AbstractDao<Screening> {

    public Screening findByTheaterAndMovie(int tId, int mId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("theaterId", tId)).add(Restrictions.eq("movieId", mId));
        return (Screening) crit.uniqueResult();
    }
}
