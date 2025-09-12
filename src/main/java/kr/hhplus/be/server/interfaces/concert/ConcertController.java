package kr.hhplus.be.server.interfaces.concert;

import kr.hhplus.be.server.application.concert.ConcertUseCase;
import kr.hhplus.be.server.interfaces.concert.dto.ConcertDateResponse;
import kr.hhplus.be.server.interfaces.concert.dto.SeatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/concerts")
public class ConcertController {
    private final ConcertUseCase concertUseCase;

    public ConcertController(ConcertUseCase concertUseCase) {
        this.concertUseCase = concertUseCase;
    }

    @GetMapping("/dates")
    public List<ConcertDateResponse> getAvailableDates() {
        return concertUseCase.getAvailableDates().stream()
                .map(ConcertDateResponse::from)
                .toList();
    }

    @GetMapping("/seats")
    public List<SeatResponse> getAvailableSeats(@RequestParam("date") String date) {
        LocalDate concertDate = LocalDate.parse(date);
        return concertUseCase.getAvailableSeats(concertDate).stream()
                .map(SeatResponse::from)
                .toList();
    }
}
