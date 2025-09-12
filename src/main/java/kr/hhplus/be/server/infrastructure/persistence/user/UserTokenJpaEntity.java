package kr.hhplus.be.server.infrastructure.persistence.user;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_token")
public class UserTokenJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String token;

    private LocalDateTime expiredAt;

    protected UserTokenJpaEntity() {}

    public UserTokenJpaEntity(Long userId, String token, LocalDateTime expiredAt) {
        this.userId = userId;
        this.token = token;
        this.expiredAt = expiredAt;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getToken() { return token; }
    public LocalDateTime getExpiredAt() { return expiredAt; }
}
