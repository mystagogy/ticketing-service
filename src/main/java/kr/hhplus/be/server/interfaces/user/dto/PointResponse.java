package kr.hhplus.be.server.interfaces.user.dto;

import kr.hhplus.be.server.domain.user.Point;

import java.math.BigDecimal;

public class PointResponse {
    private final Long userId;
    private final BigDecimal amount;

    public PointResponse(Long userId, BigDecimal amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public Long getUserId() { return userId; }
    public BigDecimal getAmount() { return amount; }

    public static PointResponse from(Point point) {
        return new PointResponse(point.getUserId(), point.getAmount());
    }
}
