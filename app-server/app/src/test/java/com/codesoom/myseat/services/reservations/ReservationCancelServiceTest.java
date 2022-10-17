//package com.codesoom.myseat.services;
//
//import com.codesoom.myseat.domain.Seat;
//import com.codesoom.myseat.domain.User;
//import com.codesoom.myseat.repositories.ReservationRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.mock;
//
//class ReservationCancelServiceTest {
//    private static final Long USER_ID = 1L;
//    private static final String NAME = "테스터";
//    private static final String EMAIL = "test@example.com";
//    private static final String PASSWORD = "1111";
//
//    private static final Long SEAT_ID = 1L;
//    private static final int SEAT_NUMBER = 1;
//
//    private ReservationCancelService service;
//
//    private final ReservationRepository reservationRepo
//            = mock(ReservationRepository.class);
//
//    @BeforeEach
//    void setUp() {
//        service = new ReservationCancelService(
//                reservationRepo
//        );
//    }
//
//    @Test
//    @DisplayName("회원 생성 성공")
//    void test() {
//        // given
//        User user = User.builder()
//                .id(USER_ID)
//                .name(NAME)
//                .email(EMAIL)
//                .password(PASSWORD)
//                .build();
//
//        Seat seat = Seat.builder()
//                .id(SEAT_ID)
//                .number(SEAT_NUMBER)
//                .status(false)
//                .user(user)
//                .build();
//
//        // when
//        service.cancelReservation(user, seat);
//        
//        // then
//        assertThat(seat.getStatus()).isEqualTo(false);
//    }
//}
