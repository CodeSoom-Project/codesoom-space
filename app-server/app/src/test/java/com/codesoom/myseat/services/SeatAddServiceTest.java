//package com.codesoom.myseat.services;
//
//import com.codesoom.myseat.domain.Seat;
//import com.codesoom.myseat.repositories.SeatRepository;
//import com.codesoom.myseat.dto.SeatAddRequest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.mock;
//
//class SeatAddServiceTest {
//    private static final Long SEAT_ID = 1L;
//    private static final int SEAT_NUMBER = 3;
//
//    private SeatAddService service;
//
//    private final SeatRepository repository = mock(SeatRepository.class);
//
//    private SeatAddRequest request;
//
//    @BeforeEach
//    void setUp() {
//        service = new SeatAddService(repository);
//    }
//
//    @Nested
//    @DisplayName("addSeat 메서드는")
//    class Describe_addSeat_method {
//        @BeforeEach
//        void setUp() {
//            request = SeatAddRequest.builder()
//                    .number(SEAT_NUMBER)
//                    .build();
//
//            given(repository.save(any(Seat.class)))
//                    .will(invocation -> {
//                        Seat seat = invocation.getArgument(0);
//
//                        return Seat.builder()
//                                .id(SEAT_ID)
//                                .number(seat.getNumber())
//                                .build();
//                    });
//        }
//
//        @Nested
//        @DisplayName("좌석 정보를 반환한다")
//        class It_returns_seat_data {
//            Seat subject() {
//                return service.addSeat(request);
//            }
//
//            @Test
//            void test() {
//                assertThat(subject().getId()).isEqualTo(SEAT_ID);
//                assertThat(subject().getNumber()).isEqualTo(SEAT_NUMBER);
//            }
//        }
//    }
//}
