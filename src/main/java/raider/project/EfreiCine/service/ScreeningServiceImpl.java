package raider.project.EfreiCine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raider.project.EfreiCine.model.Screening;
import raider.project.EfreiCine.dao.ScreeningDao;

import javax.transaction.Transactional;

@Service("screeningService")
@Transactional
public class ScreeningServiceImpl implements ScreeningService {

    @Autowired
    private ScreeningDao dao;

    public Screening getById(int id) {
        return dao.findById(id);
    }

    public Screening getScreeningByTheater(int theaterId) {
        return dao.findByTheaterId(theaterId);
    }

    public void save(Screening screening) {
        dao.save(screening);
    }
}
