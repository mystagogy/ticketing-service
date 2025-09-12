package kr.hhplus.be.server.domain.concert;

public class Seat {
    private final Long id;
    private final int seatNumber;
    private final boolean reserved;

    public Seat(Long id, int seatNumber, boolean reserved) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.reserved = reserved;
    }

    public Long getId() { return id; }
    public int getSeatNumber() { return seatNumber; }
    public boolean isReserved() { return reserved; }
}
