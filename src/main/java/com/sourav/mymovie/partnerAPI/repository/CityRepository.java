package com.sourav.mymovie.partnerAPI.repository;

import com.sourav.mymovie.partnerAPI.entity.City;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.function.Function;
@Repository
@Transactional
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select c from City c where c.cityName = :name")
    public Optional<City> findByCityName(String name);

}