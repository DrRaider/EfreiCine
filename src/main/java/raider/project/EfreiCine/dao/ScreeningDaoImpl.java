package raider.project.EfreiCine.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import raider.project.EfreiCine.model.Screening;

@Repository("screeningDao")
public class ScreeningDaoImpl extends AbstractDao<Integer, Screening> implements ScreeningDao {

    public void save(Screening screening) {
        persist(screening);
    }

    public Screening findById(int id) {
        return getByKey(id);
    }

    public Screening findByTheaterId(String tId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("theaterId", tId));
        return (Screening) crit.uniqueResult();
    }


}