package com.mehmet.akbulut.task;

import com.mehmet.akbulut.service.PersonelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CronHelper {
    @Autowired
    private PersonelService personelService;

    private  static Logger logger = LoggerFactory.getLogger(CronHelper.class);


    @Scheduled(cron="0 59 23 1 1 *", zone="Europe/Istanbul")
    public void izinGuncelle () {
      personelService.izinGuncelle();
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(now);
        String log = "Class : " +  CronHelper.class.getCanonicalName();
        log += " - " + " Tarih : "  + date;
        log += " - " + " Personel izin haklari guncellendi. ";
        logger.info(log);
    }
}
