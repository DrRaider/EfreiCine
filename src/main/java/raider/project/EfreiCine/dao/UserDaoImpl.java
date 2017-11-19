package raider.project.EfreiCine.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import raider.project.EfreiCine.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public void save(User user) {
        persist(user);
    }

    public User findById(int id) {
        return getByKey(id);
    }

    public User findBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        return (User) crit.uniqueResult();
    }


}