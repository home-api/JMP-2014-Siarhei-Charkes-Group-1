import controller.CinemaController;
import controller.TicketController;
import controller.impl.CinemaControllerImpl;
import controller.impl.TicketControllerImpl;
import model.Movie;
import model.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by test on 22.02.2015.
 */
public class Main {

    public static void main(String[] args) {
        CinemaController cinemaController = new CinemaControllerImpl();
        TicketController ticketController = new TicketControllerImpl();

        cinemaController.createMovie("movie 1", 30000);
        cinemaController.createMovie("movie 2", 45000);

        Map<Integer, Movie> tempAllMovies = new HashMap<Integer, Movie>();

        List<Movie> allMovies = cinemaController.getAllMovies();
        for (Movie movie : allMovies) {
            System.out.println(movie.getId(), movie.getName(), movie.getPrice());
            tempAllMovies.put(movie.getId(), movie);
        }

        System.out.println("Choose what movie you want to but ticket on:");
        Scanner scanner = new Scanner(System.in);
        Integer userChoice = scanner.nextInt();

        Movie selectedMovie = tempAllMovies.get(userChoice);

        Ticket ticket = ticketController.buyTicket(selectedMovie);
        System.out.println("Ticker was bought.");

        System.out.println("Checking ticket status...");
        cinemaController.checkTicker(ticket);
    }

}
