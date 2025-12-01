package kr.hhplus.be.server.interfaces.concert;

import kr.hhplus.be.server.application.concert.ReserveSeatUseCase;
import kr.hhplus.be.server.domain.concert.Reservation;
import kr.hhplus.be.server.interfaces.concert.dto.ReservationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReserveSeatUseCase reserveSeatUseCase;

    public ReservationController(ReserveSeatUseCase reserveSeatUseCase) {
        this.reserveSeatUseCase = reserveSeatUseCase;
    }

    @PostMapping
    public ReservationResponse reserveSeat(@RequestParam Long userId,
                                           @RequestParam Long seatId) {
        Reservation reservation = reserveSeatUseCase.reserveSeat(userId, seatId);
        return ReservationResponse.from(reservation);
    }
}
