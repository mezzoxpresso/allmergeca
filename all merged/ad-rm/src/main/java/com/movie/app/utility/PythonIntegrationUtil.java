package com.movie.app.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PythonIntegrationUtil {
	
	HttpURLConnection conn = null;
	
    public ArrayList<Integer> getRecommendationMovieList(String input_movie_title) {
    	 ArrayList<Integer> recommendMovieList = new ArrayList<>();
    	try
    	{
    	   String movie_title_in=input_movie_title;
    	   URL url = new URL("http://127.0.0.1:5000/getmovie/".concat(movie_title_in));
           conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("GET");
           conn.setRequestProperty("Accept", "application/json");

           if (conn.getResponseCode() != 200) {
               throw new RuntimeException("Failed : HTTP error code : "
                       + conn.getResponseCode());
           }

           BufferedReader br = new BufferedReader(new InputStreamReader(
                   (conn.getInputStream())));

           StringBuilder sb = new StringBuilder();      
           String output;
           System.out.println("Output from Python .... \n");
           while ((output = br.readLine()) != null) {
               //System.out.println(output);
               sb.append(output);
           }
           System.out.println("Json==>"+sb.toString());
                     
           JSONObject jsonResponse = new JSONObject(sb.toString());

			if (!jsonResponse.isNull("movie_1")) {
				 Integer movie_1 = Integer.valueOf(jsonResponse.getInt("movie_1"));
			 	 recommendMovieList.add(movie_1); 
			}
			 
			if (!jsonResponse.isNull("movie_2")) {
				 Integer movie_2 = Integer.valueOf(jsonResponse.getInt("movie_2"));
			 	 recommendMovieList.add(movie_2); 
			}
			 if (!jsonResponse.isNull("movie_3")) {
				 Integer movie_3 = Integer.valueOf(jsonResponse.getInt("movie_3"));
			 	 recommendMovieList.add(movie_3); 
			 }
			 
			 if (!jsonResponse.isNull("movie_4")) {
				 Integer movie_4 = Integer.valueOf(jsonResponse.getInt("movie_4"));
			 	 recommendMovieList.add(movie_4); 
			 }
	
			 if (!jsonResponse.isNull("movie_5")) {
				 Integer movie_5 = Integer.valueOf(jsonResponse.getInt("movie_5"));
				 recommendMovieList.add(movie_5);
			 }
			 
			 if (!jsonResponse.isNull("movie_6")) {
				 Integer movie_6 = Integer.valueOf(jsonResponse.getInt("movie_6"));
				 recommendMovieList.add(movie_6);
			 }
			 
			 if (!jsonResponse.isNull("movie_7")) {
				 Integer movie_7 = Integer.valueOf(jsonResponse.getInt("movie_7"));
				 recommendMovieList.add(movie_7); 
			 }
			 
			 if (!jsonResponse.isNull("movie_8")) {
				 Integer movie_8 = Integer.valueOf(jsonResponse.getInt("movie_8"));
				 recommendMovieList.add(movie_8); 
			 }

			 if (!jsonResponse.isNull("movie_9")) {
				 Integer movie_9 = Integer.valueOf(jsonResponse.getInt("movie_9"));
				 recommendMovieList.add(movie_9); 
			 }
			 
			 if (!jsonResponse.isNull("movie_10")) {
				 Integer movie_10 = Integer.valueOf(jsonResponse.getInt("movie_10"));
				 recommendMovieList.add(movie_10); 
			 }

			 if (!jsonResponse.isNull("")) {
				 Integer movie_11 = Integer.valueOf(jsonResponse.getInt("momovie_11vie_9"));
				 recommendMovieList.add(movie_11); 
			 }

			 if (!jsonResponse.isNull("movie_12")) {
				 Integer movie_12 = Integer.valueOf(jsonResponse.getInt("movie_12"));
				 recommendMovieList.add(movie_12); 
			 }

			 if (!jsonResponse.isNull("movie_13")) {
				 Integer movie_13 = Integer.valueOf(jsonResponse.getInt("movie_13"));
				 recommendMovieList.add(movie_13); 
			 }

			 if (!jsonResponse.isNull("movie_14")) {
				 Integer movie_14 = Integer.valueOf(jsonResponse.getInt("movie_14"));
				 recommendMovieList.add(movie_14); 
			 }

			 if (!jsonResponse.isNull("movie_15")) {
				 Integer movie_15 = Integer.valueOf(jsonResponse.getInt("movie_15"));
				 recommendMovieList.add(movie_15); 
			 }

			 if (!jsonResponse.isNull("movie_16")) {
				 Integer movie_16 = Integer.valueOf(jsonResponse.getInt("movie_16"));
				 recommendMovieList.add(movie_16); 
			 }

			 if (!jsonResponse.isNull("movie_17")) {
				 Integer movie_17 = Integer.valueOf(jsonResponse.getInt("movie_17"));
				 recommendMovieList.add(movie_17); 
			 }
           
           conn.disconnect();           
       } catch (MalformedURLException e) {
           e.printStackTrace();
       }catch (IOException e){
       	e.printStackTrace();
       }
       catch(Exception e) {
    	   e.printStackTrace();
       }
    	return recommendMovieList;
    } 	
       
    public ArrayList<Integer> getSentimentalMovieLis(String userid) {
   	 ArrayList<Integer> recommendMovieList = new ArrayList<>();
   	try
   	{
   	   URL url = new URL("http://127.0.0.1:5000/getSentimentalMovie/".concat(userid));
          conn = (HttpURLConnection) url.openConnection();
          conn.setRequestMethod("GET");
          conn.setRequestProperty("Accept", "application/json");

          if (conn.getResponseCode() != 200) {
              throw new RuntimeException("Failed : HTTP error code : "
                      + conn.getResponseCode());
          }

          BufferedReader br = new BufferedReader(new InputStreamReader(
                  (conn.getInputStream())));

          StringBuilder sb = new StringBuilder();      
          String output;
          System.out.println("Output from Python for Sentimental .... \n");
          while ((output = br.readLine()) != null) {
              //System.out.println(output);
              sb.append(output);
          }
          System.out.println("Json==>"+sb.toString());
          System.out.println("SB Length==>"+sb.length());
          
			
			 JSONObject jsonResponse = new JSONObject(sb.toString());
			 
			 if (!jsonResponse.isNull("movie_1")) {
				 Integer movie_1 = Integer.valueOf(jsonResponse.getInt("movie_1"));
			 	 recommendMovieList.add(movie_1); 
			 }
			 
			 if (!jsonResponse.isNull("movie_2")) {
				 Integer movie_2 = Integer.valueOf(jsonResponse.getInt("movie_2"));
			 	 recommendMovieList.add(movie_2); 
			 }
			 if (!jsonResponse.isNull("movie_3")) {
				 Integer movie_3 = Integer.valueOf(jsonResponse.getInt("movie_3"));
			 	 recommendMovieList.add(movie_3); 
			 }
			 
			 if (!jsonResponse.isNull("movie_4")) {
				 Integer movie_4 = Integer.valueOf(jsonResponse.getInt("movie_4"));
			 	 recommendMovieList.add(movie_4); 
			 }
	
			 if (!jsonResponse.isNull("movie_5")) {
				 Integer movie_5 = Integer.valueOf(jsonResponse.getInt("movie_5"));
				 recommendMovieList.add(movie_5);
			 }
			 
			 if (!jsonResponse.isNull("movie_6")) {
				 Integer movie_6 = Integer.valueOf(jsonResponse.getInt("movie_6"));
				 recommendMovieList.add(movie_6);
			 }
			 
			 if (!jsonResponse.isNull("movie_7")) {
				 Integer movie_7 = Integer.valueOf(jsonResponse.getInt("movie_7"));
				 recommendMovieList.add(movie_7); 
			 }
			 
			 if (!jsonResponse.isNull("movie_8")) {
				 Integer movie_8 = Integer.valueOf(jsonResponse.getInt("movie_8"));
				 recommendMovieList.add(movie_8); 
			 }

			 if (!jsonResponse.isNull("movie_9")) {
				 Integer movie_9 = Integer.valueOf(jsonResponse.getInt("movie_9"));
				 recommendMovieList.add(movie_9); 
			 }
			 
			 if (!jsonResponse.isNull("movie_10")) {
				 Integer movie_10 = Integer.valueOf(jsonResponse.getInt("movie_10"));
				 recommendMovieList.add(movie_10); 
			 }

			 if (!jsonResponse.isNull("")) {
				 Integer movie_11 = Integer.valueOf(jsonResponse.getInt("momovie_11vie_9"));
				 recommendMovieList.add(movie_11); 
			 }

			 if (!jsonResponse.isNull("movie_12")) {
				 Integer movie_12 = Integer.valueOf(jsonResponse.getInt("movie_12"));
				 recommendMovieList.add(movie_12); 
			 }

			 if (!jsonResponse.isNull("movie_13")) {
				 Integer movie_13 = Integer.valueOf(jsonResponse.getInt("movie_13"));
				 recommendMovieList.add(movie_13); 
			 }

			 if (!jsonResponse.isNull("movie_14")) {
				 Integer movie_14 = Integer.valueOf(jsonResponse.getInt("movie_14"));
				 recommendMovieList.add(movie_14); 
			 }

			 if (!jsonResponse.isNull("movie_15")) {
				 Integer movie_15 = Integer.valueOf(jsonResponse.getInt("movie_15"));
				 recommendMovieList.add(movie_15); 
			 }

			 if (!jsonResponse.isNull("movie_16")) {
				 Integer movie_16 = Integer.valueOf(jsonResponse.getInt("movie_16"));
				 recommendMovieList.add(movie_16); 
			 }

			 if (!jsonResponse.isNull("movie_17")) {
				 Integer movie_17 = Integer.valueOf(jsonResponse.getInt("movie_17"));
				 recommendMovieList.add(movie_17); 
			 }
			 
          conn.disconnect();           
      } catch (MalformedURLException e) {
          e.printStackTrace();
      }catch (IOException e){
      	e.printStackTrace();
      }
      catch(Exception e) {
   	   e.printStackTrace();
      }
   	return recommendMovieList;
   } 
    
}
