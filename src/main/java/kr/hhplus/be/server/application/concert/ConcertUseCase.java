package kr.hhplus.be.server.application.concert;

import kr.hhplus.be.server.domain.concert.ConcertDate;
import kr.hhplus.be.server.domain.concert.ConcertDateRepository;
import kr.hhplus.be.server.domain.concert.ConcertDateRepository;
import kr.hhplus.be.server.domain.concert.Seat;
import kr.hhplus.be.server.domain.concert.SeatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConcertUseCase {
    private final ConcertDateRepository concertDateRepository;
    private final SeatRepository seatRepository;

    public ConcertUseCase(ConcertDateRepository concertDateRepository,
                                 SeatRepository seatRepository) {
        this.concertDateRepository = concertDateRepository;
        this.seatRepository = seatRepository;
    }

    /**
     * 예약 가능한 날짜 조회
     */
    public List<ConcertDate> getAvailableDates() {
        return concertDateRepository.findAll();
    }

    /**
     * 특정 날짜의 좌석 조회
     */
    public List<Seat> getAvailableSeats(LocalDate date) {
        return seatRepository.findByConcertDate(date);
    }
}
