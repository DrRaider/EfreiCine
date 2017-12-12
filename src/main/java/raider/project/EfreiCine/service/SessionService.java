package raider.project.EfreiCine.service;

import raider.project.EfreiCine.model.Session;

public interface SessionService {
    public int countSessionByScreeningId(int screeningId);

    public Session getById(int id);

    public void save(Session session);
}
