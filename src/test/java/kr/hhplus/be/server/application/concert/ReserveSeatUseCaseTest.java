package kr.hhplus.be.server.application.concert;

import kr.hhplus.be.server.domain.concert.Reservation;
import kr.hhplus.be.server.domain.concert.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ReserveSeatUseCaseTest {
    private ReservationRepository reservationRepository;
    private ReserveSeatUseCase reserveSeatUseCase;

    @BeforeEach
    void setUp() {
        reservationRepository = mock(ReservationRepository.class);
        reserveSeatUseCase = new ReserveSeatUseCase(reservationRepository);
    }

    @Test
    void reserveSeat_success() {
        Long userId = 1L;
        Long seatId = 10L;

        // Mockito matcher 사용 시 모든 파라미터를 matcher로 통일
        when(reservationRepository.findActiveReservationBySeatId(any(Long.class), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());

        // save 호출 시 반환할 예약 객체
        Reservation savedReservation = new Reservation(1L, userId, seatId, LocalDateTime.now(), false);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(savedReservation);

        Reservation result = reserveSeatUseCase.reserveSeat(userId, seatId);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getSeatId()).isEqualTo(seatId);
        assertThat(result.isPaid()).isFalse();

        // verify
        verify(reservationRepository, times(1)).releaseExpiredReservations(any(LocalDateTime.class));
        verify(reservationRepository, times(1))
                .findActiveReservationBySeatId(any(Long.class), any(LocalDateTime.class));
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void reserveSeat_alreadyReserved_throwsException() {
        Long userId = 1L;
        Long seatId = 10L;

        Reservation existing = new Reservation(2L, 2L, seatId, LocalDateTime.now(), false);

        when(reservationRepository.findActiveReservationBySeatId(any(Long.class), any(LocalDateTime.class)))
                .thenReturn(Optional.of(existing));

        assertThrows(IllegalStateException.class,
                () -> reserveSeatUseCase.reserveSeat(userId, seatId));

        verify(reservationRepository, times(1)).releaseExpiredReservations(any(LocalDateTime.class));
        verify(reservationRepository, times(1))
                .findActiveReservationBySeatId(any(Long.class), any(LocalDateTime.class));
        verify(reservationRepository, never()).save(any(Reservation.class));
    }
}