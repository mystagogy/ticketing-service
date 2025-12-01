package kr.hhplus.be.server.application.concert;

import kr.hhplus.be.server.domain.concert.ConcertDate;
import kr.hhplus.be.server.domain.concert.ConcertDateRepository;
import kr.hhplus.be.server.domain.concert.Seat;
import kr.hhplus.be.server.domain.concert.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConcertUseCaseTest {
    private ConcertDateRepository concertDateRepository;
    private SeatRepository seatRepository;
    private ConcertUseCase concertUseCase;

    @BeforeEach
    void setUp() {
        concertDateRepository = mock(ConcertDateRepository.class);
        seatRepository = mock(SeatRepository.class);
        concertUseCase = new ConcertUseCase(concertDateRepository, seatRepository);
    }

    @Test
    void getAvailableDates_returnsConcertDates() {
        // given
        List<ConcertDate> mockDates = List.of(
                new ConcertDate(1L, LocalDate.of(2025, 9, 20)),
                new ConcertDate(2L, LocalDate.of(2025, 9, 21))
        );
        when(concertDateRepository.findAll()).thenReturn(mockDates);

        // when
        List<ConcertDate> result = concertUseCase.getAvailableDates();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getDate()).isEqualTo(LocalDate.of(2025, 9, 20));
        verify(concertDateRepository, times(1)).findAll();
    }

    @Test
    void getAvailableSeats_returnsSeats() {
        // given
        LocalDate date = LocalDate.of(2025, 9, 20);
        List<Seat> mockSeats = List.of(
                new Seat(1L, 1, false),
                new Seat(2L, 2, true)
        );
        when(seatRepository.findByConcertDate(date)).thenReturn(mockSeats);

        // when
        List<Seat> result = concertUseCase.getAvailableSeats(date);

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).isReserved()).isFalse();
        assertThat(result.get(1).isReserved()).isTrue();
        verify(seatRepository, times(1)).findByConcertDate(date);
    }

}