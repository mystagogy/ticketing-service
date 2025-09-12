package kr.hhplus.be.server.application.concert;

import kr.hhplus.be.server.domain.concert.Reservation;
import kr.hhplus.be.server.domain.concert.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReserveSeatUseCase {
    private final ReservationRepository reservationRepository;
    private static final int TEMP_RESERVATION_MINUTES = 5;

    public ReserveSeatUseCase(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation reserveSeat(Long userId, Long seatId) {
        LocalDateTime now = LocalDateTime.now();

        reservationRepository.releaseExpiredReservations(now);

        Optional<Reservation> existingReservation =
                reservationRepository.findActiveReservationBySeatId(seatId, now);

        if (existingReservation.isPresent()) {
            throw new IllegalStateException("이미 점유된 좌석입니다.");
        }

        Reservation reservation = new Reservation(
                null,
                userId,
                seatId,
                now,
                false
        );

        return reservationRepository.save(reservation);
    }
}
