package org.superbiz.moviefun.movies;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private MoviesRepository moviesRepository;
    MoviesController(MoviesRepository moviesRepository){
        this.moviesRepository=moviesRepository;
    }

    @PostMapping
    public void addMovie(@RequestBody Movie movie) {
        moviesRepository.addMovie(movie);
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable Long movieId ) {
        moviesRepository.deleteMovieId(movieId);
    }

    @GetMapping
    public List<Movie> get(
            @RequestParam(name = "start", required = false) Integer start,
            @RequestParam(name = "pagesize", required = false) Integer pagesize,
            @RequestParam(name = "field", required = false) String field,
            @RequestParam(name = "searchTerm", required = false) String searchTerm

            ) {
        if(field !=null && searchTerm !=null){
            return moviesRepository.findRange(field, searchTerm, start, pagesize);
        }
        if(start !=null && pagesize !=null){
            return moviesRepository.findAll(start,pagesize);
        }
        return moviesRepository.getMovies();
    }


    @GetMapping("/count")
    public int count(
            @RequestParam(name = "field", required = false) String field,
            @RequestParam(name = "key", required = false) String key
    ) {
        if (field != null && key != null) {
            return moviesRepository.count(field, key);
        } else {
            return moviesRepository.countAll();
        }
    }

}
