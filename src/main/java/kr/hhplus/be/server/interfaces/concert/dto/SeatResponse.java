package kr.hhplus.be.server.interfaces.concert.dto;

import kr.hhplus.be.server.domain.concert.Seat;

public class SeatResponse {
    private final Long id;
    private final int seatNumber;
    private final boolean reserved;

    public SeatResponse(Long id, int seatNumber, boolean reserved) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.reserved = reserved;
    }

    public Long getId() {
        return id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isReserved() {
        return reserved;
    }

    public static SeatResponse from(Seat seat) {
        return new SeatResponse(seat.getId(), seat.getSeatNumber(), seat.isReserved());
    }
}
