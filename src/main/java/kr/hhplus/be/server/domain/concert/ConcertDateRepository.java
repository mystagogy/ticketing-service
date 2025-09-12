package kr.hhplus.be.server.domain.concert;

import java.util.List;

public interface ConcertDateRepository {
    List<ConcertDate> findAll();
}
