package com.sourav.mymovie.partnerAPI.repository;

import com.sourav.mymovie.partnerAPI.entity.City;
import com.sourav.mymovie.partnerAPI.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    @Query("select t from Theatre t where t.")
    Optional<Theatre> findByMovieNameAndCity(@Param("movieId") Long movieId, @Param("cityId") Long cityId);

    @Query("select c from Theatre c where c.theatreName = :theatreName and c.partnerId.partnerId=:partnerId")
    public Optional<Theatre> findByTheatreNameAndPartnerId(@Param("theatreName") String theatreName, @Param("partnerId") Long partnerId);

    Optional<Theatre> findByThratreName(String theatreName);
}