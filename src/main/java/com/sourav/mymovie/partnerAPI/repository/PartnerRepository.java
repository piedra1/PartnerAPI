package com.sourav.mymovie.partnerAPI.repository;

import com.sourav.mymovie.partnerAPI.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PartnerRepository extends JpaRepository<Partner, Long> {
}