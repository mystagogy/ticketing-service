package kr.hhplus.be.server.infrastructure.persistence.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "point")
public class PointJpaEntity {
    @Id
    private Long userId;

    private BigDecimal amount;

    // getter / setter
    public Long getUserId() { return userId; }
    public BigDecimal getAmount() { return amount; }

    public void setUserId(Long userId) { this.userId = userId; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
