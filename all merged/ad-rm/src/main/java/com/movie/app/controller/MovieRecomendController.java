package com.movie.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.movie.app.model.Movie;
import com.movie.app.service.MovieService;
import com.movie.app.utility.PythonIntegrationUtil;

@Controller
@RequestMapping("/movies/")
public class MovieRecomendController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private PythonIntegrationUtil pythonIntegrationUtil;
	
	@RequestMapping("/hello")
	public String hello() {
	return "hello world";
	}
	
	/*
	 * Getting Value from DB for Rest Service
	 */
	@RequestMapping(value="/dbcall{movie_title}",method = RequestMethod.GET)
	public ResponseEntity<Movie> getMovieTitleFromDB(@RequestParam String movie_title) {
		// Optional
			System.out.println("REST Controller : "+movie_title);
		  Movie recmovie = movieService.findMovieByTitle(movie_title);
		  System.out.println("");
		  if (recmovie != null) {
			 	return new ResponseEntity<Movie>(recmovie, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/recomendation/{movie_title}")
	public String getMovieTitlesFromPython(@PathVariable(value = "movie_title") String movie_title, Model model) {
		
		System.out.println("Controller for python getMovieTitlesFromPython:"+movie_title);
		ArrayList<Integer> recommendationList = pythonIntegrationUtil.getRecommendationMovieList(movie_title);
		ArrayList<Movie> recommendUIList = new ArrayList<>();
		System.out.println("Python Return List Size: "+recommendationList.size());
		System.out.println("REST Controller"+movie_title);
		
		for(int i=0; i < recommendationList.size(); i++)
			{
			Movie movieDetails = movieService.findMovie(recommendationList.get(i));
			recommendUIList.add(movieDetails);
		}
		model.addAttribute("recommendUIList", recommendUIList);

		//return "index"; // Loading at bottom in search	
		return "recommendlist"; // Full Screen Loading
	}
	
	/*
	 * @GetMapping("/getSentimental/{user_id}") public String
	 * getSentimentalMovieListFromPython(@PathVariable(value = "user_id") String
	 * user_id, Model model) {
	 * 
	 * System.out.
	 * println("Controller for python getSentimentalMovieListFromPython :"+user_id);
	 * ArrayList<Integer> sentimentalReturnList =
	 * pythonIntegrationUtil.getSentimentalMovieLis(user_id);
	 * System.out.println("Python Return List Size: "+sentimentalReturnList.size());
	 * 
	 * HashSet<Integer>set = new HashSet<Integer>(sentimentalReturnList);
	 * List<Integer>sentimentalList = new ArrayList<Integer>(set);
	 * 
	 * ArrayList<Movie> sentimentalUIList = new ArrayList<>();
	 * 
	 * System.out.println("REST Controller"+user_id);
	 * 
	 * for(int i=0; i < sentimentalList.size(); i++) { Movie movieDetails =
	 * movieService.findMovie(sentimentalList.get(i));
	 * sentimentalUIList.add(movieDetails); }
	 * System.out.println("Sentimental UI List Size: "+sentimentalUIList.size());
	 * model.addAttribute("sentimentalUIList", sentimentalUIList);
	 * 
	 * //return "index"; // Loading at bottom in search return "sentimentallist"; //
	 * Full Screen Loading }
	 */
	

}
