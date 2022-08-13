package com.codesoom.myseat.utils;

import com.codesoom.myseat.repositories.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 스케줄링이 필요한 작업들을 관리하는 클래스
 */
@Component
@Slf4j
public class ScheduledTasks {
    private static final SimpleDateFormat dateFormat 
            = new SimpleDateFormat("HH:mm:ss");
    
    private final SeatRepository repository;

    public ScheduledTasks(SeatRepository repository) {
        this.repository = repository;
    }

    /**
     * 매일 23시에 좌석 예약 여부를 초기화한다.
     */
    @Scheduled(cron = "0 0 23 * * *")
    public void reportCurrentTime() {
        log.info("현재 시간: {}", dateFormat.format(new Date()));
        repository.seatReservationReset();
    }
}
