package com.sourav.mymovie.partnerAPI.model;

import com.sourav.mymovie.partnerAPI.entity.Discount;
import com.sourav.mymovie.partnerAPI.entity.Movie;
import com.sourav.mymovie.partnerAPI.entity.Theatre;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
@Data
public class ShowTime {

    private Time showTime;
    private Date startDate;
    private Date endDate;
    private Theatre theatre;
    private Movie movie;
    private Discount discount;
    private Seat seat;
}
