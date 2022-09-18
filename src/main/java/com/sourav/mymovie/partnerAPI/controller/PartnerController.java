package com.sourav.mymovie.partnerAPI.controller;

import com.sourav.mymovie.partnerAPI.entity.Movie;
import com.sourav.mymovie.partnerAPI.entity.Partner;
import com.sourav.mymovie.partnerAPI.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping(value = "/partner")
public class PartnerController {
    @Autowired
    PartnerService partnerService;

    @PostMapping(path = "/onboard-theatre")
    public ResponseEntity<Partner> onboardTheatre(@RequestParam Long partnerId, @RequestParam String theatreName, @RequestParam String city ) {
        Partner partner = partnerService.onboardTheatre(partnerId, theatreName, city);
        return partner != null ? ResponseEntity.ok(partner) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping(path = "/onboard-movie")
    public ResponseEntity<Movie> onboardMovie(@RequestParam Long partnerId,  @RequestParam String movieName,@RequestParam String genre,@RequestParam String language, @RequestParam String theatreName, @RequestParam String city , @RequestParam Date startDate, @RequestParam Date endDate) {
        Movie movie = partnerService.onboardMovie(partnerId, movieName, genre, language, theatreName, city, startDate, endDate);
        return movie != null ? ResponseEntity.ok(movie) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
