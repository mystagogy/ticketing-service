package kr.hhplus.be.server.domain.concert;

import java.time.LocalDate;

public class ConcertDate {
    private final Long id;
    private final LocalDate date;

    public ConcertDate(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    public Long getId() { return id; }
    public LocalDate getDate() { return date; }
}