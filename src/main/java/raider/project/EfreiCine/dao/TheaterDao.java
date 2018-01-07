package raider.project.EfreiCine.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import raider.project.EfreiCine.model.Theater;

import java.util.List;

@Repository("theaterDao")
public class TheaterDao extends AbstractDao<Theater> {

    @SuppressWarnings("unchecked")
    public List<Theater> findAll(){
        return (List<Theater>) createEntityCriteria().list();
    }

    @Override
    public Theater findById(int id) {
        return (Theater) createEntityCriteria()
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }


}
