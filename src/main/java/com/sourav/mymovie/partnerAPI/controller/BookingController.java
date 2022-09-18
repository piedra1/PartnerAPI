package com.sourav.mymovie.partnerAPI.controller;

import com.sourav.mymovie.partnerAPI.entity.*;
import com.sourav.mymovie.partnerAPI.model.BookingRequest;
import com.sourav.mymovie.partnerAPI.model.Theatre;
import com.sourav.mymovie.partnerAPI.service.BookingService;
import com.sourav.mymovie.partnerAPI.service.SeatManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;
    @Autowired
    SeatManagementService seatManagementService;

    @GetMapping(path = "/browse-cities")
    public ResponseEntity<List<City>> getCities() {
        return ResponseEntity.ok(bookingService.getCities());
    }

    @GetMapping(path = "/search-movie-by-name")
    public ResponseEntity<List<Theatre>> searchTheatresForMovie(@RequestParam String movieName, @RequestParam String city) {
        List<Theatre> theatres = bookingService.searchTheatresForMovie(movieName, city);
        return theatres != null ? ResponseEntity.ok(theatres) : ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/browse-movie")
    public ResponseEntity<List<Movie>> browseMovies(@RequestParam String language, @RequestParam String genres,@RequestParam String city) {
        List<Movie> movies = bookingService.browseMovies(language, genres, city);
        return movies != null ? ResponseEntity.ok(movies) : ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/show-timings")
    public ResponseEntity<List<ShowTime>> findShowTimings(@RequestParam Long theatreId, @RequestParam Long movieId) {
        List<ShowTime> showTimings = bookingService.findShowTimings(theatreId, movieId);
        return CollectionUtils.isEmpty(showTimings) ? ResponseEntity.noContent().build() : ResponseEntity.ok(showTimings);
    }

    @GetMapping(path = "/availableSeats")
    public ResponseEntity<List<Seat>> availableSeats(@RequestParam Time showTime, @RequestParam Long theatreId, @RequestParam Long movieId) {
        //List<Time> showTimings = bookingService.findShowTimings(showTime);
        List<com.sourav.mymovie.partnerAPI.entity.Seat> availableSeats = seatManagementService.getAvailableSeats(showTime, theatreId, movieId);
        return CollectionUtils.isEmpty(availableSeats) ? ResponseEntity.noContent().build() : ResponseEntity.ok(availableSeats);
    }

    @PostMapping(path = "/book")
    public ResponseEntity<Booking> bookTicket(@RequestBody BookingRequest bookingRequest) {
        Booking book = bookingService.book(bookingRequest);
        return ResponseEntity.ok(book);
    }



}
