package kr.hhplus.be.server.application.user;

import kr.hhplus.be.server.domain.user.UserToken;
import kr.hhplus.be.server.domain.user.UserTokenRepository;
import kr.hhplus.be.server.global.TokenGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserTokenUseCase {
    private final UserTokenRepository userTokenRepository;
    private final TokenGenerator tokenGenerator;

    public UserTokenUseCase(UserTokenRepository userTokenRepository, TokenGenerator tokenGenerator) {
        this.userTokenRepository = userTokenRepository;
        this.tokenGenerator = tokenGenerator;
    }

    public UserToken issueToken(Long userId) {
        String token = tokenGenerator.generate();
        LocalDateTime expiredAt = LocalDateTime.now().plusMinutes(30); // 30분 만료
        UserToken userToken = new UserToken(userId, token, expiredAt);

        return userTokenRepository.save(userToken);
    }
}
