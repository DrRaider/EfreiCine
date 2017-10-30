package raider.project.EfreiCine.service;

import raider.project.EfreiCine.model.Theater;

public interface TheaterService {

    public Theater getById(int id);

    public void save(Theater theater);
}
