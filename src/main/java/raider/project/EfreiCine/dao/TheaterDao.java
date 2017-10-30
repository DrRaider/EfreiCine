package raider.project.EfreiCine.dao;

import raider.project.EfreiCine.model.Theater;

public interface TheaterDao {

    void save(Theater theater);

    Theater findById(int id);
}
