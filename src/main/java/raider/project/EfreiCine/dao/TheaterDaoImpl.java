package raider.project.EfreiCine.dao;


import org.springframework.stereotype.Repository;

import raider.project.EfreiCine.model.Theater;

@Repository("theaterDao")
public class TheaterDaoImpl extends AbstractDao<Integer, Theater> implements TheaterDao {

    public void save(Theater theater) {
        persist(theater);
    }

    public Theater findById(int id) {
        return getByKey(id);
    }
}