package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.SeatRepository;
import com.codesoom.myseat.dto.SeatAddRequest;
import org.springframework.stereotype.Service;

/**
 * 좌석 관리 서비스
 */
@Service
public class SeatManagementService {
    private final SeatRepository seatRepository;

    public SeatManagementService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    /**
     * 추가된 좌석 정보를 반환한다.
     * 
     * @param seatAddRequest 추가할 좌석 정보
     * @return 좌석 정보
     */
    public Seat addSeat(SeatAddRequest seatAddRequest) {
        return seatRepository.save(
                Seat.builder()
                        .number(seatAddRequest.getNumber())
                        .build());
    }
    
    // TODO: 좌석 목록 조회
    
    // TODO: 좌석 수정
    
    // TODO: 좌석 삭제
}
