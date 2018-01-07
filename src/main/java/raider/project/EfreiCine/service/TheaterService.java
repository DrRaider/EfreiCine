package raider.project.EfreiCine.service;

import raider.project.EfreiCine.model.Theater;

import java.util.List;

public interface TheaterService {

    Theater getById(int id);

    List<Theater> findAll();

    void save(Theater theater);
}
