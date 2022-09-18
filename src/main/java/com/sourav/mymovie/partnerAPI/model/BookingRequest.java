package com.sourav.mymovie.partnerAPI.model;

import lombok.Data;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
public class BookingRequest {

    private String bookingId;
    private Seat seat;
    private Date bookingDate;
    private Time showTime;
    private Long theatreId;
    private Integer movieId;

}
