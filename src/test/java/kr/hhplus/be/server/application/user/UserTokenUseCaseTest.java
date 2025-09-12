package kr.hhplus.be.server.application.user;

import kr.hhplus.be.server.domain.user.UserToken;
import kr.hhplus.be.server.domain.user.UserTokenRepository;
import kr.hhplus.be.server.global.TokenGenerator;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;


class UserTokenUseCaseTest {
    @Test
    void issueToken_success() {
        // given
        UserTokenRepository mockRepository = mock(UserTokenRepository.class);
        TokenGenerator mockGenerator = mock(TokenGenerator.class);

        when(mockGenerator.generate()).thenReturn("mock-token");
        when(mockRepository.save(any(UserToken.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        UserTokenUseCase useCase = new UserTokenUseCase(mockRepository, mockGenerator);

        // when
        UserToken result = useCase.issueToken(1L);

        // then
        assertThat(result.getUserId()).isEqualTo(1L);
        assertThat(result.getToken()).isEqualTo("mock-token");
        assertThat(result.getExpiredAt()).isAfter(LocalDateTime.now());

        // verify repository.save 호출 확인
        verify(mockRepository, times(1)).save(any(UserToken.class));
    }
}