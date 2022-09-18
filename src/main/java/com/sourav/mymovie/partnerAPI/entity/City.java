package com.sourav.mymovie.partnerAPI.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(schema = "pr", name = "CITY")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_id", nullable = false)
    private Long cityId;
    @Column(name = "CITY_NAME")
    private String cityName;
    @OneToMany(mappedBy = "theatreId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Theatre> theatres;

    public void addTheatre(Theatre theatre) {
        this.theatres.add(theatre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        City city = (City) o;
        return cityId != null && Objects.equals(cityId, city.cityId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
