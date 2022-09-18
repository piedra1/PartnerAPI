package com.sourav.mymovie.partnerAPI.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SHOWTIME_ID", nullable = false)
    private Long showTimeId;

    @Column(name = "SHOWTIME")
    private Time showTime;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "DISCOUNT_ID")
    private Discount discount;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShowTime showTime = (ShowTime) o;
        return showTimeId != null && Objects.equals(showTimeId, showTime.showTimeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
