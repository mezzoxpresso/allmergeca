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
import com.movie.app.utility.PythonIntegrationUtil;
import com.movie.app.model.User;
import com.movie.app.model.UserSession;


@Controller
public class HomeController {
  
    @Autowired
    private UserService userService;

    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepository movieRepo;
	
    @Autowired
	private PythonIntegrationUtil pythonIntegrationUtil;

	
   @RequestMapping(value={"/", "/home", "/movies/home"}, method = RequestMethod.GET)
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


       return "home";

    }
    
    @GetMapping(value="/movies/search")
    public String search(Model model, @Param("keyword") String keyword) {
    
    	ArrayList<Movie> recommendUIList = new ArrayList<>();
        List<Movie> movieList = movieService.listAllMovies(keyword);
        model.addAttribute("movieList", movieList);
        model.addAttribute("keyword", keyword);
        String movieIdToPython=null;
        //Code Added for ML Returned Movie Listing - Recommendation
		if(!movieList.isEmpty())
        {
        	Movie mostMatchedMovie = movieList.get(0);
        	movieIdToPython = Integer.toString(mostMatchedMovie.getMovieId());
        	System.out.println("movieIdToPython ==>"+movieIdToPython);
        	ArrayList<Integer> recommendationList = pythonIntegrationUtil.getRecommendationMovieList(movieIdToPython);
        	
        	System.out.println("Python Return List Size: "+recommendationList.size());
		
        	for(int i=0; i < recommendationList.size(); i++)
				{
					int j=1;
					Movie movieDetails = movieService.findMovie(recommendationList.get(i));
					System.out.println("Selected Movie : "+movieDetails.getMovieTitle());
					if(!movieDetails.getMovieTitle().equalsIgnoreCase(keyword))
						recommendUIList.add(movieDetails);
					if(recommendUIList.size()==4)
						break;
				}
        }
        model.addAttribute("recommendUIList", recommendUIList);
        model.addAttribute("movieIdToPython", movieIdToPython);
        
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
    
    @GetMapping(value="/movies/getAllRecommendation/{movieId}")
    public String getAllRecommendation(@PathVariable String movieId, Model model) {
    	System.out.println("getAllRecommendation "+movieId);
    	ArrayList<Movie> recommendUIList = new ArrayList<>();
       	ArrayList<Integer> recommendationList = pythonIntegrationUtil.getRecommendationMovieList(movieId);
        System.out.println("Python Return List Size: "+recommendationList.size());
		for(int i=0; i < recommendationList.size(); i++)
				{
					Movie movieDetails = movieService.findMovie(recommendationList.get(i));
					System.out.println("Selected Movie : "+movieDetails.getMovieTitle());
					if(movieDetails.getMovieId()!= Integer.parseInt(movieId))
						recommendUIList.add(movieDetails);
/*					if(recommendUIList.size()==16)
						break;*/
				}
        model.addAttribute("recommendUIList", recommendUIList);
        return "viewall_recommendation";
    }

    
}






