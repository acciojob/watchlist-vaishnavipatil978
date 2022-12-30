package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    HashMap <String, Movie> movieDb;
    HashMap <String , Director> directorDb;
    HashMap <String,List<String>> movieDirectorPair;

    public MovieRepository(){
        movieDb= new HashMap<>();
        directorDb = new HashMap<>();
        movieDirectorPair = new HashMap<>();
    }

    String addMovieToDb(Movie movie){
        movieDb.put(movie.getName(),movie);
        return "Movie successfully added! ";
    }

    String addDirectorToDb(Director director){
        directorDb.put(director.getName(),director);
        return "Director successfully added! ";
    }

    String addMovieDirectorPair(String movie,String director){
        if(movieDb.containsKey(movie) && directorDb.containsKey(director)){

            List<String> moviesList = new ArrayList<>();

            if(movieDirectorPair.containsKey(director)) moviesList = movieDirectorPair.get(director);

            moviesList.add(movie);
            movieDirectorPair.put(director,moviesList);
            return "The pair has been successfully added! ";
        }
        return null;
    }

    Movie getMovieFromDb(String name){
        if(movieDb.containsKey(name)) return movieDb.get(name);

        return null;
    }

    Director getDirectorFromDb(String name){
        if(directorDb.containsKey(name)) return directorDb.get(name);

        return null;
    }

    List<String> getMoviesByDirector(String director){
        if(movieDirectorPair.containsKey(director)) return movieDirectorPair.get(director);

        return null;
    }

    List<String> getAllMovies(){
        return new ArrayList<>(movieDb.keySet());
    }

    void deleteDirectorByName(String director){
        try{
            directorDb.remove(director);

            for(String movie: movieDirectorPair.get(director)){
                if(movieDb.containsKey(movie)) movieDb.remove(movie);
            }

            movieDirectorPair.remove(director);
        }
        catch(Exception e){

        }
    }

    void deleteAllDirectors(){
        try{
            directorDb.clear();
            List<String> movies = new ArrayList<>();

            for(String director: movieDirectorPair.keySet()){
                for(String movie : movieDirectorPair.get(director)){
                    movies.add(movie);
                }
            }

            for(String movie : movies){
                if(movieDb.containsKey(movie)) movieDb.remove(movie);
            }

            movieDirectorPair.clear();
        }
        catch(Exception e){

        }
    }
}
