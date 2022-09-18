package com.sourav.mymovie.partnerAPI.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    private String customerName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    List<Booking> bookings = new java.util.ArrayList<>();

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
