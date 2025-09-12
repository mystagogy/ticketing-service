package kr.hhplus.be.server.interfaces.user.dto;

import java.time.LocalDateTime;

public class UserTokenResponse {
    private final String token;
    private final LocalDateTime expiredAt;

    public UserTokenResponse(String token, LocalDateTime expiredAt) {
        this.token = token;
        this.expiredAt = expiredAt;
    }

    public String getToken() { return token; }
    public LocalDateTime getExpiredAt() { return expiredAt; }
}
