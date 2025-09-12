package kr.hhplus.be.server.domain.user;

import java.time.LocalDateTime;

public class UserToken {
    private final Long userId;
    private final String token;
    private final LocalDateTime expiredAt;

    public UserToken(Long userId, String token, LocalDateTime expiredAt) {
        this.userId = userId;
        this.token = token;
        this.expiredAt = expiredAt;
    }

    public Long getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }
}
