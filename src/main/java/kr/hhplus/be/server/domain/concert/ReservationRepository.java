package kr.hhplus.be.server.domain.concert;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReservationRepository {
    Optional<Reservation> findActiveReservationBySeatId(Long seatId, LocalDateTime now);

    Reservation save(Reservation reservation);

    void releaseExpiredReservations(LocalDateTime now);
}
