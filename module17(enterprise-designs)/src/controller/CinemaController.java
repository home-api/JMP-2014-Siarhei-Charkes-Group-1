package controller;

import model.Movie;
import model.Ticket;

import java.util.List;

/**
 * Created by test on 22.02.2015.
 */
public interface CinemaController {
    void createMovie(String s, int i);

    void checkTicker(Ticket ticket);

    List<Movie> getAllMovies();
}
