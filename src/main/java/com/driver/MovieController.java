package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

//    Add a movie: POST /movies/add-movie
//    Pass the Movie object as request body
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addMovie

    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        String result = movieService.addNewMovie(movie);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

//    Add a director: POST /movies/add-director
//    Pass the Director object as request body
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addDirector

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        String result = movieService.addNewDirector(director);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

//    Pair an existing movie and director: PUT /movies/add-movie-director-pair
//    Pass movie name and director name as request parameters
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addMovieDirectorPair

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movieName, @RequestParam("director") String directorName){
        String res = movieService.addMovieDirectorPair(movieName,directorName);

        if(res==null) return new ResponseEntity<>("Movie or Director do not exist...",HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }

//    Get Movie by movie name: GET /movies/get-movie-by-name/{name}
//    Pass movie name as path parameter
//    Return Movie object wrapped in a ResponseEntity object
//    Controller Name - getMovieByName

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name){
        Movie result = movieService.getMovie(name);

        if(result==null) return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

//    Get Director by director name: GET /movies/get-director-by-name/{name}
//    Pass director name as path parameter
//    Return Director object wrapped in a ResponseEntity object
//    Controller Name - getDirectorByName

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name){
        Director result = movieService.getDirector(name);

        if(result==null) return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

//    Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
//    Pass director name as path parameter
//    Return List of movies name(List()) wrapped in a ResponseEntity object
//    Controller Name - getMoviesByDirectorName

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String director){
        List<String> movies = movieService.getMoviesByDirector(director);

        if(movies.size()==0) return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(movies,HttpStatus.OK);
    }

//    Get List of all movies added: GET /movies/get-all-movies
//    No params or body required
//    Return List of movies name(List()) wrapped in a ResponseEntity object
//    Controller Name - findAllMovies

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        return new ResponseEntity<>(movieService.getAllMovies(),HttpStatus.OK);
    }

//    Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
//    Pass director’s name as request parameter
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteDirectorByName

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director") String director){
        movieService.deleteDirectorByName(director);
        return new ResponseEntity<>("Director has been delete! ",HttpStatus.OK);
    }

//    Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
//    No params or body required
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteAllDirectors
// (Note that there can be some movies on your watchlist that aren’t mapped to any of the director. Make sure you do not remove them.)

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All directors has been delete! ",HttpStatus.OK);
    }
}
