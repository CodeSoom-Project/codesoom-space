package com.codesoom.myseat.services.reservations.retrospectives;

import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.repositories.RetrospectiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class RetrospectiveDetailServiceTest {
    private RetrospectiveDetailService service;

    @Mock
    private RetrospectiveRepository retrospectiveRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new RetrospectiveDetailService(retrospectiveRepo);
    }

    @Test
    @DisplayName("retrospective 메서드는 조회된 회고를 반환한다.")
    void retrospective_returns() {
        Retrospective mockRetrospective = Retrospective.builder()
                .id(2L)
                .content("잘했다.")
                .build();

        given(retrospectiveRepo.findByReservationId(1L))
                .willReturn(Optional.of(mockRetrospective));

        Retrospective retrospective
                = service.retrospective(1L);

        assertThat(retrospective.getId()).isEqualTo(2L);
        assertThat(retrospective.getContent()).isEqualTo("잘했다.");
    }
}
