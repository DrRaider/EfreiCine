package raider.project.EfreiCine.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.DateFormat;
import  java.text.SimpleDateFormat;
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

        User user = new User();
        user = userTheater.getUser();
        Theater theater = new Theater();
        theater = userTheater.getTheater();

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

        System.out.println("First Name : " + user.getFirstName());
        System.out.println("Last Name : " + user.getLastName());
        System.out.println("SSO ID : " + user.getSsoId());
        System.out.println("Password : " + user.getPassword());
        System.out.println("Email : " + user.getEmail());
        System.out.println("Checking UserProfiles....");

        System.out.println("UsrProfiles : " + user.getUserProfiles());


        model.addAttribute("success", "User " + user.getFirstName() + " has been registered successfully");
        return "registrationsuccess";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchPage(ModelMap model) {

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
        Movie myMovie = TheMovieDbAPI.retrieveMovieData(id);
        myMovie.setBackdropPath(Movie.IMG_PATH_PREFIX + myMovie.getBackdropPath());
        myMovie.setPosterPath(Movie.IMG_PATH_PREFIX + myMovie.getPosterPath());

        //Add screening data on the page and push it on POST :)
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
    public String saveRegistration(@Valid ScreeningSession screeningSession, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            System.out.println("There are errors");
            return "movie";
        }
        Screening screening = screeningSession.getScreening();
        Session session = screeningSession.getSession();

        Screening test =  screeningService.getByTheaterAndMovie(screening.getTheaterId(), screening.getMovieId());
        if (null == test) {
            screeningService.save(screening);
        }
        else {
            screening.setId(test.getId());
        }

        if (sessionService.countSessionByScreeningId(screening.getId()) <= 3){
            session.setScreeningId(screening.getId());
            sessionService.save(session);
        }


        return "movie";
    }

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