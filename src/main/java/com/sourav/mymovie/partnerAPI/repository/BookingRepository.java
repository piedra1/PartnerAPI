package com.sourav.mymovie.partnerAPI.repository;

import com.sourav.mymovie.partnerAPI.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}