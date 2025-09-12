package kr.hhplus.be.server.interfaces.user;

import kr.hhplus.be.server.application.user.UserTokenUseCase;
import kr.hhplus.be.server.domain.user.UserToken;
import kr.hhplus.be.server.interfaces.user.dto.UserTokenResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserTokenController {

    private final UserTokenUseCase userTokenUseCase;

    public UserTokenController(UserTokenUseCase userTokenUseCase) {
        this.userTokenUseCase = userTokenUseCase;
    }

    @PostMapping("/{userId}/token")
    public UserTokenResponse issueToken(@PathVariable Long userId) {
        UserToken token = userTokenUseCase.issueToken(userId);
        return new UserTokenResponse(token.getToken(), token.getExpiredAt());
    }
}