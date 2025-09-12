package kr.hhplus.be.server.interfaces.concert.dto;

import kr.hhplus.be.server.domain.concert.Reservation;

import java.time.LocalDateTime;

public class ReservationResponse {
    private final Long id;
    private final Long userId;
    private final Long seatId;
    private final LocalDateTime reservedAt;
    private final boolean paid;

    public ReservationResponse(Long id, Long userId, Long seatId, LocalDateTime reservedAt, boolean paid) {
        this.id = id;
        this.userId = userId;
        this.seatId = seatId;
        this.reservedAt = reservedAt;
        this.paid = paid;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getSeatId() { return seatId; }
    public LocalDateTime getReservedAt() { return reservedAt; }
    public boolean isPaid() { return paid; }

    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getUserId(),
                reservation.getSeatId(),
                reservation.getReservedAt(),
                reservation.isPaid()
        );
    }
}
