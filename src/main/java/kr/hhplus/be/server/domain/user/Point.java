package kr.hhplus.be.server.domain.user;

import java.math.BigDecimal;

public class Point {
    private final Long userId;
    private BigDecimal amount;

    public Point(Long userId, BigDecimal amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public Long getUserId() { return userId; }
    public BigDecimal getAmount() { return amount; }

    public Point charge(BigDecimal chargeAmount) {
        if(chargeAmount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("충전 금액은 0보다 커야 합니다.");
        }
        BigDecimal newAmount = this.amount.add(chargeAmount);
        return new Point(this.userId, newAmount);
    }
}
