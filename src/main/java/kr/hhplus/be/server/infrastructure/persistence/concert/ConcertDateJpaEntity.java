package kr.hhplus.be.server.infrastructure.persistence.concert;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "concert_date")
public class ConcertDateJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    protected ConcertDateJpaEntity() {}

    public ConcertDateJpaEntity(LocalDate date) {
        this.date = date;
    }

    public Long getId() { return id; }
    public LocalDate getDate() { return date; }
}
