package kr.hhplus.be.server.infrastructure.persistence.concert;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<ReservationJpaEntity, Long> {

    List<ReservationJpaEntity> findBySeatIdAndPaidIsFalseAndReservedAtAfter(Long seatId, LocalDateTime now);

    List<ReservationJpaEntity> findByPaidIsFalseAndReservedAtBefore(LocalDateTime now);
}
