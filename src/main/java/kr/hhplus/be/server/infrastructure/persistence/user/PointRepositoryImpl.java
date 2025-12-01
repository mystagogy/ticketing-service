package kr.hhplus.be.server.infrastructure.persistence.user;

import kr.hhplus.be.server.domain.user.Point;
import kr.hhplus.be.server.domain.user.PointRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PointRepositoryImpl implements PointRepository {
    private final PointJpaRepository jpaRepository;

    public PointRepositoryImpl(PointJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Point> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId)
                .map(entity -> new Point(entity.getUserId(), entity.getAmount()));
    }

    @Override
    public Point save(Point point) {
        PointJpaEntity entity = new PointJpaEntity();
        entity.setUserId(point.getUserId());
        entity.setAmount(point.getAmount());

        PointJpaEntity saved = jpaRepository.save(entity);
        return new Point(saved.getUserId(), saved.getAmount());
    }
}
