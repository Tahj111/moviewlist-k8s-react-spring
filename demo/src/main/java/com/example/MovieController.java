package com.example.myspringcontainer;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private List<Movie> movies = new ArrayList<>();

    // GET /movies → list all movies
    @GetMapping
    public List<Movie> getMovies() {
        return movies;
    }

    // POST /movies → add a new movie
    @PostMapping
    public String addMovie(@RequestBody Movie movie) {
        movies.add(movie);
        return "Added movie: " + movie.getTitle();
    }

    // PUT /movies/watch?title=Inception → mark a movie as watched
    @PutMapping("/watch")
    public String markWatched(@RequestParam String title) {
        Optional<Movie> found = movies.stream()
                .filter(m -> m.getTitle().equalsIgnoreCase(title))
                .findFirst();
        if (found.isPresent()) {
            found.get().setWatched(true);
            return "Marked as watched: " + title;
        } else {
            return "Movie not found: " + title;
        }
    }

    // DELETE /movies?title=Inception → remove a movie
    @DeleteMapping
    public String deleteMovie(@RequestParam String title) {
        boolean removed = movies.removeIf(m -> m.getTitle().equalsIgnoreCase(title));
        return removed ? "Deleted movie: " + title : "Movie not found: " + title;
    }
}
