package raider.project.EfreiCine.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import raider.project.EfreiCine.model.*;
import raider.project.EfreiCine.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;


    @RequestMapping(value = "/search", method=RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody List<MovieQuick> searchMovies(
            @RequestParam String movie,
            @RequestParam(value="use_external_api", required=false) boolean useExternal)
            throws IOException {
        List<MovieQuick> searchResults = movieService.searchByTitle(movie).stream()
                .map(MovieQuick::from)
                .collect(Collectors.toList());

        if(useExternal) {
            Set<Integer> localMovies = searchResults.stream()
                    .map(m -> m.id)
                    .collect(Collectors.toSet());

            for (BaseMovie m : TheMovieDbAPI.searchMovie(movie).results) {
                if(!localMovies.contains(m.id))
                    searchResults.add(MovieQuick.from(m));
            }
        }

        return searchResults;
    }

    @RequestMapping(value = "/search", method=RequestMethod.GET)
    public String searchMoviesHtml(
            @RequestParam(required=false) String movie,
            @RequestParam(value="use_external_api", required=false) boolean useExternal,
            ModelMap model)
            throws IOException {
        if(movie == null)
            return "search";

        List<MovieQuick> searchResults = searchMovies(movie, useExternal);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);
        String jsonInString = mapper.writeValueAsString(searchResults);
        model.addAttribute("search_results", jsonInString);
        return "search";
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody Movie getMovie(@PathVariable("id") int id) throws IOException {
        Movie myMovie = movieService.loadFromId(id);
        myMovie.setBackdropPath(Movie.IMG_PATH_PREFIX + myMovie.getBackdropPath());
        myMovie.setPosterPath(Movie.IMG_PATH_PREFIX + myMovie.getPosterPath());

        return myMovie;
    }
}