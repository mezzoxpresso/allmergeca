package com.movie.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.movie.app.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer>{
	
	@Query(value ="SELECT  SUM(r.rating) FROM Review r WHERE r.movie_movie_id=:id AND r.rating>0 GROUP BY r.movie_movie_id", nativeQuery = true)
	public double getTotalRating(int id);
	
	
	@Query(value ="SELECT COUNT(r.user_user_id) FROM Review r WHERE r.movie_movie_id =:id AND r.rating>0", nativeQuery = true)
	public int getTotalNumber(@Param("id")int id);


@Query("SELECT m FROM Movie m WHERE m.movieTitle LIKE %?1%"
            + " OR m.actor1_name LIKE %?1%"
            + " OR m.actor2_name LIKE %?1%"
            + " OR m.director LIKE %?1%")
           
public List<Movie> search(String keyword);

@Query(value ="SELECT * FROM Movie ORDER BY movie_release_date DESC LIMIT 4", nativeQuery = true)  
public List<Movie> getNewMovies();

@Query(value ="SELECT * FROM Movie ORDER BY movie_release_date DESC LIMIT 16", nativeQuery = true)  
public List<Movie> getAllNewMovies();

@Query(value ="SELECT movie_movie_id, COUNT(*) FROM Review GROUP BY movie_movie_id HAVING COUNT(*)>=4 LIMIT 4", nativeQuery = true) 
public List<Integer> findTrendingMovieId();

@Query(value ="SELECT movie_movie_id, COUNT(*) FROM Review GROUP BY movie_movie_id HAVING COUNT(*)>=4 LIMIT 16", nativeQuery = true) 
public List<Integer> findAllTrendingMovieId();


@Query("SELECT m FROM Movie m where m.movieTitle =?1")
public Movie findByTitle(String movie_title);

@Query(value="SELECT movie_movie_id, AVG(rating) FROM review GROUP BY movie_movie_id HAVING AVG(rating) >=4 LIMIT 4", nativeQuery=true)
public List<Integer> findHighlyRatedMovieId();

@Query(value="SELECT movie_movie_id, AVG(rating) FROM review GROUP BY movie_movie_id HAVING AVG(rating) >= 4 LIMIT 16", nativeQuery=true)
public List<Integer> findAllHighlyRatedMovieId();
}
