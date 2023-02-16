package com.movie.app.controller;


import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




import lombok.Value;
import com.movie.app.repository.MovieRepository;
import com.movie.app.model.Movie;
import com.movie.app.model.MovieCategory;
import com.movie.app.service.MovieCategoryService;
import com.movie.app.service.MovieService;
import com.movie.app.service.UserService;
import com.movie.app.model.User;
import com.movie.app.model.UserSession;


@Controller
@RequestMapping("/user")
public class UserHomeController {
  
    @Autowired
    private UserService userService;

    @Autowired
    MovieService movieService;

    @Autowired
   MovieRepository movieRepo;

   @GetMapping(value="/movies/home")
   public String home(Model model) {
       List<Movie> movieList = movieService.findAllMovies();
       model.addAttribute("movieList", movieList);

      
       List<Movie> newMovieList = movieService.listNewMovies();
       model.addAttribute("newMovieList", newMovieList);

       List<Movie> trendingMovieList=new ArrayList<Movie>();

       List<Integer> trendingMovieId=movieService.getTrendingMovieId();
       for(Integer movieId: trendingMovieId){
        
            Movie trendingMovie=movieService.findMovie(movieId);

            trendingMovieList.add(trendingMovie);

            model.addAttribute("trendingMovieList", trendingMovieList);
        }

        List<Movie> highlyRatedMovieList=new ArrayList<Movie>();

       List<Integer> highlyRatedMovieId=movieService.getHighlyRatedMovieId();
       for(Integer movieId: highlyRatedMovieId){
        
            Movie highlyRatedMovie=movieService.findMovie(movieId);

            highlyRatedMovieList.add(highlyRatedMovie);

            model.addAttribute("highlyRatedMovieList", highlyRatedMovieList);
        }


       return "user_home";

    }
    
    @GetMapping(value="/movies/search")
    public String search(Model model, @Param("keyword") String keyword) {
    
        List<Movie> movieList = movieService.listAllMovies(keyword);
        model.addAttribute("movieList", movieList);
        model.addAttribute("keyword", keyword);

        
        return "index";
    }



    @GetMapping(value="/newReleaseList")
    public String newRelease(Model model){
        List<Movie> newReleaseList=movieService.listAllNewMovies();
        model.addAttribute("newReleaseList", newReleaseList);

        return "new_release";
    }

    @GetMapping(value="/trendingMovieList")
    public String trendingMovie(Model model){

        List<Movie> trendingMovieList=new ArrayList<Movie>();

       List<Integer> trendingMovieId=movieService.getTrendingMovieId();
       for(Integer movieId: trendingMovieId){
        
            Movie trendingMovie=movieService.findMovie(movieId);

            trendingMovieList.add(trendingMovie);

            model.addAttribute("trendingMovieList", trendingMovieList);
        }
       
        return "trending_movie";
    }

    @GetMapping(value="/highlyRatedMovieList")
    public String highlyRatedMovie(Model model){

        List<Movie> highlyRatedMovieList=new ArrayList<Movie>();

       List<Integer> highlyRatedMovieId=movieService.getHighlyRatedMovieId();
       for(Integer movieId: highlyRatedMovieId){
        
            Movie highlyRatedMovie=movieService.findMovie(movieId);

            highlyRatedMovieList.add(highlyRatedMovie);

            model.addAttribute("highlyRatedMovieList", highlyRatedMovieList);
        }
       
        return "highly_rated_movie";
    }






}