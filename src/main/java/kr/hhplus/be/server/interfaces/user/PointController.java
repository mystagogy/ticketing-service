package kr.hhplus.be.server.interfaces.user;

import kr.hhplus.be.server.application.user.PointUseCase;
import kr.hhplus.be.server.domain.user.Point;
import kr.hhplus.be.server.interfaces.user.dto.PointResponse;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/points")
public class PointController {
    private final PointUseCase pointUseCase;

    public PointController(PointUseCase pointUseCase) {
        this.pointUseCase = pointUseCase;
    }

    @GetMapping("/{userId}")
    public PointResponse getPoint(@PathVariable Long userId) {
        Point point = pointUseCase.getPoint(userId);
        return PointResponse.from(point);
    }

    @PostMapping("/{userId}/charge")
    public PointResponse charge(@PathVariable Long userId,
                                @RequestParam BigDecimal amount) {
        Point point = pointUseCase.charge(userId, amount);
        return PointResponse.from(point);
    }
}
