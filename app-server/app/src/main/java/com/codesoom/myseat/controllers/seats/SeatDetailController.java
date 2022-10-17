//package com.codesoom.myseat.controllers;
//
//import com.codesoom.myseat.domain.Seat;
//import com.codesoom.myseat.domain.User;
//import com.codesoom.myseat.dto.SeatDetailResponse;
//import com.codesoom.myseat.security.UserAuthentication;
//import com.codesoom.myseat.services.SeatService;
//import com.codesoom.myseat.services.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
///**
// * 좌석 상세 조회 컨트롤러
// */
//@RestController
//@RequestMapping("/seat")
//@CrossOrigin
//@Slf4j
//public class SeatDetailController {
//    private final UserService userService;
//    private final SeatService seatService;
//
//    public SeatDetailController(
//            UserService userService,
//            SeatService seatService
//    ) {
//        this.userService = userService;
//        this.seatService = seatService;
//    }
//
//    /**
//     * 좌석 상세 조회 후 상태코드 200을 응답한다.
//     *
//     * @param number 조회할 좌석 번호
//     * @return 좌석 예약 정보
//     */
//    @GetMapping("{number}")
//    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("isAuthenticated()")
//    public SeatDetailResponse seatDetail(
//            @AuthenticationPrincipal UserAuthentication principal,
//            @PathVariable int number
//    ) {
//        log.info("number: " + number);
//
//        User user = userService.findById(principal.getId());
//
//        return toResponse(user, seatService.findSeat(number));
//    }
//
//    private SeatDetailResponse toResponse(User user, Seat seat) {
//        String name = "";
//        Boolean mySeat = false;
//
//        User owner = seat.getUser();
//        if(owner != null) {
//            name = owner.getName();
//            
//            if(user.getName().equals(name)) {
//                mySeat = true;
//            }
//        }
//
//        log.info("name: " + name);
//        log.info("mySeat: " + mySeat);
//
//        return SeatDetailResponse.builder()
//                .number(seat.getNumber())
//                .name(name)
//                .mySeat(mySeat)
//                .build();
//    }
//}
