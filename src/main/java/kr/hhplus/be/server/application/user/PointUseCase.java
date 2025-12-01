package kr.hhplus.be.server.application.user;

import kr.hhplus.be.server.domain.user.Point;
import kr.hhplus.be.server.domain.user.PointRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PointUseCase {
    private final PointRepository pointRepository;

    public PointUseCase(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public Point getPoint(Long userId) {
        return pointRepository.findByUserId(userId)
                .orElseGet(() -> pointRepository.save(new Point(userId, BigDecimal.ZERO)));
    }

    public Point charge(Long userId, BigDecimal amount) {
        Point point = getPoint(userId);
        point.charge(amount);
        return pointRepository.save(point);
    }
}
