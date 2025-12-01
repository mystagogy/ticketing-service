package kr.hhplus.be.server.infrastructure.persistence.user;

import kr.hhplus.be.server.domain.user.UserToken;
import kr.hhplus.be.server.domain.user.UserTokenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserTokenRepositoryImpl implements UserTokenRepository {
    private final UserTokenJpaRepository jpaRepository;

    public UserTokenRepositoryImpl(UserTokenJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public UserToken save(UserToken userToken) {
        UserTokenJpaEntity entity = new UserTokenJpaEntity(
                userToken.getUserId(),
                userToken.getToken(),
                userToken.getExpiredAt()
        );
        UserTokenJpaEntity saved = jpaRepository.save(entity);

        return new UserToken(saved.getUserId(), saved.getToken(), saved.getExpiredAt());
    }
}
