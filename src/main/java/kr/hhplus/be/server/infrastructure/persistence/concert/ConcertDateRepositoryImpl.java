package kr.hhplus.be.server.infrastructure.persistence.concert;

import kr.hhplus.be.server.domain.concert.ConcertDate;
import kr.hhplus.be.server.domain.concert.ConcertDateRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ConcertDateRepositoryImpl implements ConcertDateRepository {

    private final ConcertDateJpaRepository jpaRepository;

    public ConcertDateRepositoryImpl(ConcertDateJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }


    @Override
    public List<ConcertDate> findAll() {
        return jpaRepository.findAll().stream()
                .map(entity -> new ConcertDate(entity.getId(), entity.getDate()))
                .collect(Collectors.toList());
    }

}
