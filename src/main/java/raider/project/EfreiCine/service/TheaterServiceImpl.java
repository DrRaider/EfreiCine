package raider.project.EfreiCine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raider.project.EfreiCine.dao.TheaterDao;
import raider.project.EfreiCine.model.Theater;

import javax.transaction.Transactional;
import java.util.List;


@Service("theaterService")
@Transactional
public class TheaterServiceImpl implements TheaterService {

    @Autowired
    TheaterDao dao;

    public List<Theater> findAll() {
        return dao.findAll();
    }

    public Theater getById(int id) {
        return dao.findById(id);
    }

    public void save(Theater theater) {
        dao.save(theater);
    }

}
