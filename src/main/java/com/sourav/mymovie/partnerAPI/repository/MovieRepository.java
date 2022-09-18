package com.sourav.mymovie.partnerAPI.repository;

import com.sourav.mymovie.partnerAPI.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("select m from movie where m.movieName=:movieName")
    Optional<Movie> findByMovieName(@Param("movieName") String movieName);
}