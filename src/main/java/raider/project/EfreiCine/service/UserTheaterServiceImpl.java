package raider.project.EfreiCine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raider.project.EfreiCine.dao.UserTheaterDao;
import raider.project.EfreiCine.model.UserTheater;

@Service("userTheaterService")
@Transactional
public class UserTheaterServiceImpl implements UserTheaterService {

    @Autowired
    private UserTheaterDao dao;

    public void save(UserTheater userTheater){
        dao.save(userTheater);
    }

    public UserTheater findByUserId(int id) {
        return dao.findByUserId(id);
    }

}
