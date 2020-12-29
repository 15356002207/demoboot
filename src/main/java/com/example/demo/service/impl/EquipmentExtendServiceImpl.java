package com.example.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EquipmentExtendServiceImpl   {


    @Scheduled(cron ="0 39 11 * * ?" )
    public void test() {
        log.info("test");
    }

    @Scheduled(cron ="13 * * * * ?" )
    public void testcron() {
        log.info("testcron");
    }

}
