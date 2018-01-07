package raider.project.EfreiCine.service;

import raider.project.EfreiCine.model.Session;

import java.util.List;

public interface SessionService {
    long countSessionByScreeningId(int screeningId);

    Session getById(int id);

    List<Session> findByScreeningId(int sId);
    void save(Session session);
}
