package kr.hhplus.be.server.infrastructure.persistence.concert;

import jakarta.persistence.*;

@Entity
@Table(name = "seat")
public class SeatJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int seatNumber;

    private boolean reserved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_date_id")
    private ConcertDateJpaEntity concertDate;

    protected SeatJpaEntity() {}

    public SeatJpaEntity(int seatNumber, boolean reserved, ConcertDateJpaEntity concertDate) {
        this.seatNumber = seatNumber;
        this.reserved = reserved;
        this.concertDate = concertDate;
    }

    public Long getId() { return id; }
    public int getSeatNumber() { return seatNumber; }
    public boolean isReserved() { return reserved; }
    public ConcertDateJpaEntity getConcertDate() { return concertDate; }
}