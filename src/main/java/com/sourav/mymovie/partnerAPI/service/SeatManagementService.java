package com.sourav.mymovie.partnerAPI.service;

import com.sourav.mymovie.partnerAPI.entity.Seat;
import com.sourav.mymovie.partnerAPI.entity.ShowTime;
import com.sourav.mymovie.partnerAPI.entity.Theatre;
import com.sourav.mymovie.partnerAPI.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatManagementService {

    @Autowired
    PointRepository pointRepository;
    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    private ShowTimeRepository showTimeRepository;

    public List<Seat> getAvailableSeats(Time showTime, Long theatreId, Long movieId) {

        List<Seat> sets = new ArrayList<>();
        Optional<ShowTime> showTimeAndMovie = showTimeRepository.findByTheatreAndShowTimeAndMovie(theatreId, movieId, showTime);
        Optional<Theatre> theatreObj = theatreRepository.findById(showTimeAndMovie.get().getTheatre().getTheatreId());
        if (theatreObj.isPresent()) {
            Optional<List<Seat>> seat = seatRepository.findByTheatreAndStatus(theatreObj.get().getTheatreId());
            if (seat.isPresent()) {
                sets = seat.get().stream().filter(point -> point.getStatus().equals("Available")).collect(Collectors.toList());
            }
        }
        return sets;
    }


}
