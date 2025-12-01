package kr.hhplus.be.server.application.user;

import kr.hhplus.be.server.domain.user.Point;
import kr.hhplus.be.server.domain.user.PointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PointUseCaseTest {
    private PointRepository pointRepository;
    private PointUseCase pointUseCase;

    @BeforeEach
    void setUp() {
        pointRepository = mock(PointRepository.class);
        pointUseCase = new PointUseCase(pointRepository);
    }

    @Test
    void getPoint_existingUser_returnsPoint() {
        Long userId = 1L;
        Point existing = new Point(userId, BigDecimal.valueOf(100));

        when(pointRepository.findByUserId(userId)).thenReturn(Optional.of(existing));

        Point result = pointUseCase.getPoint(userId);

        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getAmount()).isEqualByComparingTo("100");

        verify(pointRepository, times(1)).findByUserId(userId);
        verify(pointRepository, never()).save(any());
    }

    @Test
    void getPoint_newUser_createsPoint() {
        Long userId = 2L;

        when(pointRepository.findByUserId(userId)).thenReturn(Optional.empty());
        when(pointRepository.save(any(Point.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Point result = pointUseCase.getPoint(userId);

        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getAmount()).isEqualByComparingTo("0");

        verify(pointRepository, times(1)).findByUserId(userId);
        verify(pointRepository, times(1)).save(any(Point.class));
    }

    @Test
    void charge_validAmount_success() {
        Long userId = 3L;
        Point existing = new Point(userId, BigDecimal.valueOf(50));

        when(pointRepository.findByUserId(userId)).thenReturn(Optional.of(existing));
        when(pointRepository.save(any(Point.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Point result = pointUseCase.charge(userId, BigDecimal.valueOf(30));

        assertThat(result.getAmount()).isEqualByComparingTo("80");

        verify(pointRepository, times(1)).findByUserId(userId);
        verify(pointRepository, times(1)).save(any(Point.class));
    }

    @Test
    void charge_negativeAmount_throwsException() {
        Long userId = 4L;

        Point existing = new Point(userId, BigDecimal.valueOf(50));
        when(pointRepository.findByUserId(userId)).thenReturn(Optional.of(existing));

        assertThrows(IllegalArgumentException.class,
                () -> pointUseCase.charge(userId, BigDecimal.valueOf(-10)));

        verify(pointRepository, never()).save(any(Point.class));
    }
}