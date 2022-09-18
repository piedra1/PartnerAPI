package com.sourav.mymovie.partnerAPI.service;

import com.sourav.mymovie.partnerAPI.entity.*;
import com.sourav.mymovie.partnerAPI.publisher.MessagePublisher;
import com.sourav.mymovie.partnerAPI.repository.CityRepository;
import com.sourav.mymovie.partnerAPI.repository.MovieRepository;
import com.sourav.mymovie.partnerAPI.repository.PartnerRepository;
import com.sourav.mymovie.partnerAPI.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class PartnerService {
    @Autowired
    PartnerRepository partnerRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    MovieRepository movieRepository;

    public Partner onboardTheatre(Long partnerId, String theatreName, String city) {

        Optional<Partner> repositoryById = partnerRepository.findById(partnerId);
        if(repositoryById.isPresent()) {
            Partner partner = repositoryById.get();
            Optional<Theatre> theatre = theatreRepository.findByThratreName(theatreName);
            if (theatre.isPresent()) {
                partner.addTheatre(theatre.get());
            } else {
                 theatre = Optional.of(new Theatre());
                if (theatre.get().getCity() != null) {
                    Optional<City> byCityName = cityRepository.findByCityName(city);
                    theatre.get().setCity(byCityName.get());
                } else {
                    City city1 = new City();
                    city1.setCityName(city);
                    theatre.get().setCity(city1);
                }
                theatre.get().setTheatreName(theatreName);
            }
            theatreRepository.save(theatre.get());
            partner.getTheatreIds().add(theatre.get());
         //   messagePublisher.publishMessage(theatre, seatOptional.get());
            Partner partner1 = partnerRepository.saveAndFlush(partner);
            return partner1;

        }

        return null;
    }

    public Movie onboardMovie(Long partnerId, String movieName, String genre, String language, String theatreName, String city, Date startDate, Date endDate) {
        Optional<Partner> repositoryById = partnerRepository.findById(partnerId);
        if(repositoryById.isPresent()) {
            Partner partner = repositoryById.get();
            Optional<Theatre> theatre = theatreRepository.findByTheatreNameAndPartnerId(theatreName,partnerId);
            if (theatre.isPresent()) {

                Theatre entity = theatre.get();
                Movie movie = new Movie();
                movie.setMovieName(movieName);
                movie.setGenre(genre);
                movie.setLanguage(language);
                movie.setTheatre(entity);

                // If the
                Movie movie1 = movieRepository.saveAndFlush(movie);
            //    messagePublisher.publishMessage(movie1, seatOptional.get());
                return movie1;
            }

        }
        return null;
    }
}
