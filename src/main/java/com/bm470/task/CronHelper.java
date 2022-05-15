package com.bm470.task;

import com.bm470.model.Personel;
import com.bm470.service.PersonelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
@Component
public class CronHelper {
    @Autowired
    private PersonelService personelService;

    private  static Logger logger = LoggerFactory.getLogger(CronHelper.class);
    @Scheduled(cron="0 0 0 * * *", zone="Europe/Istanbul")
    public void izinGuncelle () {
      personelService.izinGuncelle();
      logger.info("Personel İzin Hakları Güncellendi");
    }
}
