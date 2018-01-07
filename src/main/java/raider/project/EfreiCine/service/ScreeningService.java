package raider.project.EfreiCine.service;

import raider.project.EfreiCine.model.Screening;

import java.util.List;

public interface ScreeningService {
    Screening getByTheaterAndMovie(int theaterId, int movieId);

    List<Screening> getByTheater(int tId);

    List<Screening> getByMovie(int mId);

    Screening getById(int id);

    void save(Screening screening);
}
