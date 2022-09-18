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
@Table(schema = "pr", name = "PARTNER")
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PARTNER_ID", nullable = false)
    private Long partnerId;
    @Column(name = "PARTNER_NAME")
    private String partnerName;
    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Theatre> theatreIds;

    public void addTheatre(Theatre theatre) {
        theatreIds.add(theatre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Partner partner = (Partner) o;
        return partnerId != null && Objects.equals(partnerId, partner.partnerId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
