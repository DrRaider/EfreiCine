package raider.project.EfreiCine.service;

import raider.project.EfreiCine.model.Session;

public interface SessionService {
    long countSessionByScreeningId(int screeningId);

    Session getById(int id);

    void save(Session session);
}
