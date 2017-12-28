package raider.project.EfreiCine.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import raider.project.EfreiCine.model.Session;

@Repository("sessionDao")
public class SessionDao extends AbstractDao<Session>{

    public long countByScreeningId(int sId) {
        Criteria crit = createEntityCriteria()
            .add(Restrictions.eq("screeningId", sId))
            .setProjection(Projections.rowCount());

        return (long) crit.uniqueResult();

    }
}
