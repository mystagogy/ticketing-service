package kr.hhplus.be.server.infrastructure.persistence.concert;

import kr.hhplus.be.server.domain.concert.Reservation;
import kr.hhplus.be.server.domain.concert.ReservationRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {
    private final ReservationJpaRepository jpaRepository;

    public ReservationRepositoryImpl(ReservationJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Reservation> findActiveReservationBySeatId(Long seatId, LocalDateTime now) {
        return jpaRepository.findBySeatIdAndPaidIsFalseAndReservedAtAfter(seatId, now.minusMinutes(5))
                .stream()
                .findFirst()
                .map(entity -> new Reservation(
                        entity.getId(),
                        entity.getUserId(),
                        entity.getSeatId(),
                        entity.getReservedAt(),
                        entity.isPaid()
                ));
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationJpaEntity entity = new ReservationJpaEntity();
        entity.setUserId(reservation.getUserId());
        entity.setSeatId(reservation.getSeatId());
        entity.setReservedAt(reservation.getReservedAt());
        entity.setPaid(reservation.isPaid());

        ReservationJpaEntity saved = jpaRepository.save(entity);

        return new Reservation(
                saved.getId(),
                saved.getUserId(),
                saved.getSeatId(),
                saved.getReservedAt(),
                saved.isPaid()
        );
    }

    @Override
    public void releaseExpiredReservations(LocalDateTime threshold) {
        jpaRepository.findByPaidIsFalseAndReservedAtBefore(threshold)
                .forEach(jpaRepository::delete);
    }
}
