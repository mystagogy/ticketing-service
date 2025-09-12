package kr.hhplus.be.server.domain.user;

import java.util.Optional;

public interface PointRepository {
    Optional<Point> findByUserId(Long userId);
    Point save(Point point);
}
