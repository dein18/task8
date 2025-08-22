package ru.dse.task8.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LimitResetScheduler {

    private final LimitService limitService;

    // Каждый день в полночь
    @Scheduled(cron = "${schedulers.cronExpression}")
    public void resetLimits() {
        limitService.resetAllLimits();
    }
}