package raider.project.EfreiCine.service;

import raider.project.EfreiCine.model.UserTheater;

public interface UserTheaterService {
    void save(UserTheater userTheater);

     UserTheater findByUserId(int id);
}
