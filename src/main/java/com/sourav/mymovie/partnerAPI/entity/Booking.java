package com.sourav.mymovie.partnerAPI.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(schema = "cr", name = "BOOKING")
public class Booking {
    @Id
    @Column(name = "BOOKING_ID", nullable = false)
    private Long id;

    private String bookingId;

    @ManyToOne
    @JoinColumn(name = "customer_customer_id")
    private Customer customer;
    @Column(name = "BOOKING_STATUS")
    private String bookingStatus;
    @Column(name = "PAYMENT_AMT")
    private Long paymentAmt;
    @Column(name = "SHOWTIME")
    private Time showTime;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Booking booking = (Booking) o;
        return bookingId != null && Objects.equals(bookingId, booking.bookingId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}