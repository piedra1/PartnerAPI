package com.sourav.mymovie.partnerAPI.repository;

import com.sourav.mymovie.partnerAPI.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query("select s from Seat s where s.theatreId=:theatreId")
    Optional<List<Seat>> findByTheatreAndStatus(Long theatreId);
    @Query("select s from Seat s where s.x=:x and s.y=:y and s.showTime.showTime=:showTime")
    Optional<Seat> findBySeatlocationAndShowTime(Integer x, Integer y, Time showTime);
}