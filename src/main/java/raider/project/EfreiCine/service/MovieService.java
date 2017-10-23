package raider.project.EfreiCine.service;

import java.io.IOException;

public interface MovieService {

    public String getMovieName();

    public void setMovieName(String movieName);

    public void movieSearch() throws IOException;

}


