package raider.project.EfreiCine.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import raider.project.EfreiCine.model.Session;

@Repository("sessionDao")
public class SessionDao extends AbstractDao<Session>{

    public int countByScreeningId(int sId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("screeningId", sId));
        crit.setProjection(Projections.rowCount());
        return (int) crit.uniqueResult();
    }
}
