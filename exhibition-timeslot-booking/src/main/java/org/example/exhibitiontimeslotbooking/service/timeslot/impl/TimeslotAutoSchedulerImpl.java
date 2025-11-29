package org.example.exhibitiontimeslotbooking.service.timeslot.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.service.timeslot.TimeslotService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TimeslotAutoSchedulerImpl {

    private final TimeslotService timeslotService;

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void changeAutoTimeslot() {
        LocalDateTime now = LocalDateTime.now();

        timeslotService.autoUpdateTimeslotStatus();

        System.out.println("타임슬롯 자동 업데이트 완료: " + now);
    }
}
