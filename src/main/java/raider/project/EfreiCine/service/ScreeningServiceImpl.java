package raider.project.EfreiCine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raider.project.EfreiCine.model.Screening;
import raider.project.EfreiCine.dao.ScreeningDao;

import javax.transaction.Transactional;
import java.util.List;

@Service("screeningService")
@Transactional
public class ScreeningServiceImpl implements ScreeningService {

    @Autowired
    private ScreeningDao dao;

    public List<Screening> getByTheater(int tId) {
        return dao.findByTheater(tId);
    }

    public List<Screening> getByMovie(int mId) {
        return dao.findByMovie(mId);
    }

    public Screening getById(int id) {
        return dao.findById(id);
    }

    public Screening getByTheaterAndMovie(int theaterId, int movieId) {
        return dao.findByTheaterAndMovie(theaterId, movieId);
    }

    public void save(Screening screening) {
        dao.save(screening);
    }
}
