package com.sourav.mymovie.partnerAPI.service;

import com.sourav.mymovie.partnerAPI.entity.*;
import com.sourav.mymovie.partnerAPI.model.BookingRequest;
import com.sourav.mymovie.partnerAPI.model.Theatre;
import com.sourav.mymovie.partnerAPI.publisher.MessagePublisher;
import com.sourav.mymovie.partnerAPI.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookingService {

    @Autowired
    CityRepository cityRepository;
    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ShowTimeRepository showTimeRepository;
    @Autowired
    MessagePublisher messagePublisher;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    SeatRepository seatRepository;


    public List<City> getCities() {
        return cityRepository.findAll();
    }

    public List<Theatre> searchTheatresForMovie(String movieName, String city) {
        List<com.sourav.mymovie.partnerAPI.model.Theatre> theatres = new ArrayList<>();
        Optional<Movie> optionalMovie = movieRepository.findByMovieName(movieName);
        if (optionalMovie.isPresent()) {
            Optional<City> optionalCity = cityRepository.findByCityName(city);

            theatres = optionalCity.map(value -> value.getTheatres().stream().filter(t -> t.getMovies().contains(optionalMovie.get())).map(t -> {
                Theatre theatre = new Theatre();
                BeanUtils.copyProperties(t, theatre);
                return theatre;
            }).collect(Collectors.toList()))
                    .orElseGet(() -> optionalCity.get().getTheatres().stream().filter(t -> t.getMovies().contains(optionalMovie.get())).map(t -> {
                Theatre theatre = new Theatre();
                BeanUtils.copyProperties(t, theatre);
                return theatre;
            }).collect(Collectors.toList()));

        }
        return theatres;
    }

    public List<ShowTime> findShowTimings(Long theatreId, Long movieId) {

        Optional<List<ShowTime>> showTimes = showTimeRepository.getShowTime(theatreId, movieId);
        return showTimes.map(times -> times.stream().collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    public List<Movie> browseMovies(String language, String genres, String city) {
        List<Movie> movieList = new ArrayList<>();
        Optional<City> optionalCity = cityRepository.findByCityName(city);
        if(optionalCity.isPresent()) {
            Set<Set<Movie>> setStream = optionalCity.get().getTheatres().stream().map(com.sourav.mymovie.partnerAPI.entity.Theatre::getMovies).collect(Collectors.toSet());
            movieList = setStream.stream()
                    .flatMap(s -> s.stream().filter(s1 -> s1.getGenre().equals(genres)))
                    .filter(s2 -> s2.getLanguage().equals(language)).toList();

        }
        return movieList;
    }

    public Booking book(BookingRequest bookingRequest) {
        String bookingId = UUID.randomUUID().toString();
        bookingRequest.setBookingId(bookingId);
        Booking booking = new Booking();
        booking.setBookingId(bookingId);

        bookingRepository.save(booking);

        Optional<ShowTime> showTime = showTimeRepository.findByTheatreAndShowTimeAndMovieAndShowTime(bookingRequest.getShowTime().getTime(),bookingRequest.getTheatreId(),bookingRequest.getMovieId());
        if (showTime.isPresent()) {
            Optional<Seat> seatOptional = seatRepository.findBySeatlocationAndShowTime(bookingRequest.getSeat().getX(), bookingRequest.getSeat().getY(),bookingRequest.getShowTime());
            if (seatOptional.isPresent()) {
                 seatOptional.get().setStatus("OnHold");
                messagePublisher.publishMessage(bookingRequest,seatOptional.get());
                 seatRepository.saveAndFlush(seatOptional.get());
            }
        }
        return booking;
    }
}
