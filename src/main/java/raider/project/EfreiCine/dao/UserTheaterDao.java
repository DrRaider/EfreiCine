package raider.project.EfreiCine.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import raider.project.EfreiCine.model.User;
import raider.project.EfreiCine.model.UserTheater;


@Repository("userTheaterDao")
public class UserTheaterDao extends AbstractDao<UserTheater> {

    public UserTheater findByUserId(User user) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("user", user));
        return (UserTheater) crit.uniqueResult();
    }

    @Override
    public void save(UserTheater entity) {
        getSession().merge(entity);
    }
}