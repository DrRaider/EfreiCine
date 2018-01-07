package raider.project.EfreiCine.controller;

import java.io.IOException;
import java.util.*;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.BaseResultsPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import raider.project.EfreiCine.model.*;
import raider.project.EfreiCine.service.*;


@Controller
public class HelloWorldController {

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    UserService userService;

    @Autowired
    MovieService movieService;

    @Autowired
    TheaterService theaterService;

    @Autowired
    ScreeningService screeningService;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserTheaterService userTheaterService;

    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        model.addAttribute("greeting", "Hi, Welcome to mysite");
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "admin";
    }

    @RequestMapping(value = "/db", method = RequestMethod.GET)
    public String dbaPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "dba";
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accessDenied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String newRegistration(ModelMap model) {
        ConcatUserTheater userTheater = new ConcatUserTheater();
        userTheater.setUser(new User());
        userTheater.setTheater(new Theater());
        model.addAttribute("userTheater", userTheater);
        return "register";
    }

    /*
     * This method will be called on form submission, handling POST request It
     * also validates the user input
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegistration(@Valid ConcatUserTheater userTheater,BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            System.out.println("There are errors");
            return "register";
        }

        User user = userTheater.getUser();
        Theater theater = userTheater.getTheater();

        UserProfile pro = new UserProfile();
        pro.setId(1);
        pro.setType("USER");
        Set<UserProfile> profile = new HashSet<>();
        profile.add(pro);
        user.setUserProfiles(profile);
        userService.save(user);

        Set<User> userId = new HashSet<>();
        userId.add(user);
        System.out.println("profile :" + theater);
        theater.setUserTheaters(userId);
        System.out.println("profile :" + theater);
        theaterService.save(theater);

        UserTheater linkedId = new UserTheater();
        linkedId.setUserId(user.getId());
        linkedId.setTheaterId(theater.getId());
        userTheaterService.save(linkedId);

        model.addAttribute("success", "User " + user.getFirstName() + " has been registered successfully");
        return "registrationsuccess";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchPage() {
        return "search";
    }

    @RequestMapping(value = "/search", method=RequestMethod.POST)
    public String searchResult(@RequestParam String movie, ModelMap model) throws IOException {
        BaseResultsPage<BaseMovie> searchResults = TheMovieDbAPI.searchMovie(movie);
        for(BaseMovie m: searchResults.results) {
            m.backdrop_path = Movie.IMG_PATH_PREFIX + m.backdrop_path;
            m.poster_path = Movie.IMG_PATH_PREFIX + m.poster_path;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);
        String jsonInString = mapper.writeValueAsString(searchResults);
        model.addAttribute("search_results", jsonInString);

        return "search";
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public String getScreenings(@PathVariable("id") int id, ModelMap model) throws IOException {
        Movie myMovie = movieService.findByTmdbId(id);
        if (myMovie == null)
            myMovie = TheMovieDbAPI.retrieveMovieData(id);

        myMovie.setBackdropPath(Movie.IMG_PATH_PREFIX + myMovie.getBackdropPath());
        myMovie.setPosterPath(Movie.IMG_PATH_PREFIX + myMovie.getPosterPath());

        ScreeningSession screeningSession = new ScreeningSession();
        screeningSession.setScreening(new Screening());
        screeningSession.setSession(new Session());

        model.addAttribute("screeningSession", screeningSession);
        model.addAttribute("movie", myMovie);
        model.addAttribute(
                "theater",
                userTheaterService.findByUserId(
                        userService.findBySso(
                                getPrincipal()
                        ).getId()
                )
        );
        return "/movie";
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.POST)
    public String saveScreening(@PathVariable("id") int id,
                                @RequestParam String hour,
                                @Valid ScreeningSession screeningSession,
                                BindingResult result
    ) throws IOException, ParseException {

        if (result.hasErrors()) {
            System.out.println("There are errors");
            return "/movie";
        }

        movieService.save(id);

        Screening screening = screeningSession.getScreening();
        screening.setMovieId(id);

        Session session = screeningSession.getSession();
        DateFormat sdf = new SimpleDateFormat("hh:mm");
        Date date = sdf.parse(hour);
        session.setHour(date);

        Screening test =  screeningService.getByTheaterAndMovie(screening.getTheaterId(), id);
        if (null == test)
            screeningService.save(screening);

        else
            screening.setId(test.getId());


        if (sessionService.countSessionByScreeningId(screening.getId()) < 3) {
            session.setScreeningId(screening.getId());
            sessionService.save(session);
        }
        //TODO: send back message if >= 3
        return "/movie";
    }

    //Select theater first
    @RequestMapping(value = "/byTheater", method = RequestMethod.GET)
    public String searchByTheater(ModelMap model) {
        model.addAttribute("theaters", theaterService.findAll());
        return "byTheater";
    }

    //Select film after theater
    @RequestMapping(value = "/theater/{id}", method = RequestMethod.GET)
    public String TheaterMovie(@PathVariable("id") int id, ModelMap model) throws ParseException {
        List<Screening> list = screeningService.getByTheater(id);
        List<Movie> movies = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        for (Screening s : list) {
            if (sdf.parse(s.getEndDate()).compareTo(today) >= 0
                    && sdf.parse(s.getStartDate()).compareTo(today) <= 0)
                movies.add(movieService.findByTmdbId(s.getMovieId()));
        }
        model.addAttribute("screenings", list);
        model.addAttribute("movies", movies);
        model.addAttribute("theaterId", id);
        return "/theater";
    }

    //Select film first
    @RequestMapping(value = "/byMovie", method = RequestMethod.GET)
    public String searchByMovie(ModelMap model) {
        model.addAttribute("movies", movieService.findAll());
        return "byMovie";
    }

    //Select theater after film
    @RequestMapping(value = "/film/{id}", method = RequestMethod.GET)
    public String MovieTheater(@PathVariable("id") int id, ModelMap model) throws ParseException {
        List<Screening> list = screeningService.getByMovie(id);
        List<Theater> theaters = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        for (Screening s : list) {
            if (sdf.parse(s.getEndDate()).compareTo(today) >= 0
                    && sdf.parse(s.getStartDate()).compareTo(today) <= 0)
                theaters.add(theaterService.getById(s.getTheaterId()));
        }
        model.addAttribute("screenings", list);
        model.addAttribute("theaters", theaters);
        model.addAttribute("movieId", id);
        return "/film";
    }


    @RequestMapping(value = "/sessions/{movie}/{theater}", method = RequestMethod.GET)
    public String sessions(@PathVariable("movie") int movie,
                           @PathVariable("theater") int theater,
                           ModelMap model
    ) throws IOException {
        if (movie == 0)
            return "byMovie";

        if (theater == 0)
            return "byTheater";

        Screening screening = screeningService.getByTheaterAndMovie(theater, movie);
        model.addAttribute("theater", theaterService.getById(theater));
        model.addAttribute("movie", movieService.findByTmdbId(movie));
        model.addAttribute("screening", screening);
        model.addAttribute("sessions", sessionService.findByScreeningId(screening.getId()));

        return "/sessions";
    }


    // todo : (if user logged in and user of theater then crud) (views) (3 max sessions)
    private String getPrincipal(){
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}