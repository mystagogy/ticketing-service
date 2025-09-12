package kr.hhplus.be.server.interfaces.concert.dto;

import kr.hhplus.be.server.domain.concert.ConcertDate;

import java.time.LocalDate;

public class ConcertDateResponse {
    private final Long id;
    private final LocalDate date;

    public ConcertDateResponse(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public static ConcertDateResponse from(ConcertDate concertDate) {
        return new ConcertDateResponse(concertDate.getId(), concertDate.getDate());
    }
}
