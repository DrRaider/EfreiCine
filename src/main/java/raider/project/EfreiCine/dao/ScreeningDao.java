package raider.project.EfreiCine.dao;

import raider.project.EfreiCine.model.Screening;

public interface ScreeningDao {
    void save(Screening screening);

    Screening findById(int id);

    Screening findByTheaterId(int tId);
}
