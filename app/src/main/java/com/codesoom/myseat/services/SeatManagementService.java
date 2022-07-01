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

    // TODO: 좌석 추가
    public Seat addSeat(SeatAddRequest seatAddRequest) {
        return null;
    }
    
    // TODO: 좌석 목록 조회
    
    // TODO: 좌석 수정
    
    // TODO: 좌석 삭제
}
