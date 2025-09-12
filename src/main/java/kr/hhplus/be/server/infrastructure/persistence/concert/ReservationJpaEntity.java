package kr.hhplus.be.server.infrastructure.persistence.concert;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class ReservationJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long seatId;
    private LocalDateTime reservedAt;
    private boolean paid;

    // getter / setter
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getSeatId() { return seatId; }
    public LocalDateTime getReservedAt() { return reservedAt; }
    public boolean isPaid() { return paid; }

    public void setUserId(Long userId) { this.userId = userId; }
    public void setSeatId(Long seatId) { this.seatId = seatId; }
    public void setReservedAt(LocalDateTime reservedAt) { this.reservedAt = reservedAt; }
    public void setPaid(boolean paid) { this.paid = paid; }
}
