package org.example.exhibitiontimeslotbooking.service.exhibition.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.service.exhibition.ExhibitionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExhibitionAutoSchedulerImpl {

    private final ExhibitionService exhibitionService;

    @Scheduled(cron = "5 * * * * *")
    @Transactional
    public void changeAutoExhibition() {
        LocalDateTime now = LocalDateTime.now();

        exhibitionService.autoUpdateExhibitionStatus();

        System.out.println("전시회장 상태가 자동 업데이트 되었습니다." + now);
    }
}
