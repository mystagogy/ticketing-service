package kr.hhplus.be.server.infrastructure.persistence.concert;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SeatJpaRepository extends JpaRepository<SeatJpaEntity, Long> {
    List<SeatJpaEntity> findByConcertDate_Date(LocalDate date);
}