package com.sourav.mymovie.partnerAPI.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(schema = "cr", name = "THEATRE")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "THEATRE_ID", nullable = false)
    private Long theatreId;
    @Column(name = "THEATRE_NAME")
    private String theatreName;

    @ManyToOne
    @JoinColumn(name = "partner_partner_id")
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "city_city_id")
    private City city;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "THEATRE_MOVIE", joinColumns =
            {@JoinColumn(referencedColumnName = "MOVIE_ID")}
            , inverseJoinColumns = {@JoinColumn(referencedColumnName = "THEATRE_ID")})
    @ToString.Exclude
    private Set<Movie> movies;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ShowTime> showTimes;

    @OneToMany(mappedBy = "theatre")
    private List<Seat> seats;

    @Column(name = "MAX_CAPACITY")
    private Integer maxCapacity;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Long theatreId) {
        this.theatreId = theatreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Theatre theatre = (Theatre) o;
        return theatreId != null && Objects.equals(theatreId, theatre.theatreId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
