package com.sourav.mymovie.partnerAPI.repository;

import com.sourav.mymovie.partnerAPI.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {

    @Query("select t from showtime t where t.theatre.theatreId=:theatreId and t.movie.movieId=:movieId")
    Optional<List<ShowTime>> getShowTime(@Param("theatreId") Long theatreId, @Param("movieId") Long movieId);

    @Query("select t from showtime t where t.theatre.theatreId=:theatreId and t.movie.movieId=:movieId")
    Optional<ShowTime> findByTheatreAndShowTimeAndMovie(Long theatreId, Long movieId, Time showTime);
    @Query("select t from showtime t where t.theatre.theatreId=:theatreId and t.movie.movieId=:movieId and t.showTime=:time")
    Optional<ShowTime> findByTheatreAndShowTimeAndMovieAndShowTime(long time, Long theatreId, Integer movieId);
}