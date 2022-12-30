package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    String addNewMovie(Movie movie){
        return movieRepository.addMovieToDb(movie);
    }

    String addNewDirector(Director director){
        return movieRepository.addDirectorToDb(director);
    }

    String addMovieDirectorPair(String movie,String director){
        return movieRepository.addMovieDirectorPair(movie,director);
    }

    Movie getMovie(String name){
        return movieRepository.getMovieFromDb(name);
    }

    Director getDirector(String name){
        return movieRepository.getDirectorFromDb(name);
    }

    List<String> getMoviesByDirector(String director){ return movieRepository.getMoviesByDirector(director);}

    List<String> getAllMovies(){ return movieRepository.getAllMovies();}

    void deleteDirectorByName(String director){ movieRepository.deleteDirectorByName(director); }

    void deleteAllDirectors(){ movieRepository.deleteAllDirectors(); }
}
