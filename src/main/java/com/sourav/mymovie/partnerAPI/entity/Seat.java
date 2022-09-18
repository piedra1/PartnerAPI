package com.sourav.mymovie.partnerAPI.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Data
@ToString
@Entity
@Table(name = "SEAT")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SEAT_ID", nullable = false)
    private Long id;

    @Column(name = "X")
    private Integer x;
    @Column(name = "Y")
    private Integer y;
    @ManyToOne
    @JoinColumn(name = "THEATRE_ID")
    private Theatre theatre;

    @ManyToOne
    @JoinColumn(name = "show_time_showtime_id")
    private ShowTime showTime;

    @Column(name = "SEAT_STATUS")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Seat seat = (Seat) o;
        return id != null && Objects.equals(id, seat.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
