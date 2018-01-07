package raider.project.EfreiCine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raider.project.EfreiCine.dao.SessionDao;
import raider.project.EfreiCine.model.Session;

import javax.transaction.Transactional;
import java.util.List;

@Service("sessionService")
@Transactional
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionDao dao;

    public long countSessionByScreeningId(int sessionId) {
        return dao.countByScreeningId(sessionId);
    }

    public Session getById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Session> findByScreeningId(int sId) {
        return dao.getByScreeningId(sId);
    }

    public void save(Session session) {
        dao.save(session);
    }
}
