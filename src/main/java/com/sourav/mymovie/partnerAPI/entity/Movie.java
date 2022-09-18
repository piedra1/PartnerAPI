package com.sourav.mymovie.partnerAPI.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@RequiredArgsConstructor
@Entity
@Table(schema = "pr", name = "MOVIE")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MOVIE_ID", nullable = false)
    private Long movieId;
    @Column(name = "MOVIE_NAME")
    private String movieName;
    @Column(name = "LANGUAGE")
    private String language;
    @Column(name = "GENRE")
    private String genre;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ShowTime> showTimes;

    @ManyToOne
    @JoinColumn(name = "theatre_theatre_id")
    private Theatre theatre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Movie movie = (Movie) o;
        return movieId != null && Objects.equals(movieId, movie.movieId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
