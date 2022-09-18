package com.sourav.mymovie.partnerAPI.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DISCOUNT_ID", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "discount")
    private ShowTime showTime;

    @Column(name = "DISCOUNT_PERCENT")
    private Integer discountPercent;

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

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
        Discount discount = (Discount) o;
        return id != null && Objects.equals(id, discount.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
