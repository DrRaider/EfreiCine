package raider.project.EfreiCine.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import raider.project.EfreiCine.model.UserTheater;


@Repository("userTheaterDao")
public class UserTheaterDao extends AbstractDao<UserTheater> {

    public UserTheater findByUserId(int userId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("userId", userId));
        return (UserTheater) crit.uniqueResult();
    }
}