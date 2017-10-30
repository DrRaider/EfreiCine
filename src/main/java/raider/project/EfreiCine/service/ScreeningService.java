package raider.project.EfreiCine.service;

import raider.project.EfreiCine.model.Screening;

public interface ScreeningService {
    public Screening getScreeningByTheater(int theaterId);

    public Screening getById(int id);

    public void save(Screening screening);
}
