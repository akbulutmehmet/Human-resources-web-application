package com.bm470.dao;

import com.bm470.config.AppConfig;
import com.bm470.config.WebAppInitializer;
import com.bm470.config.WebConfig;
import com.bm470.model.Personel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppInitializer.class, AppConfig.class,
        WebConfig.class})
@Transactional
@WebAppConfiguration
public class PersonelDAOTest {

    @Autowired
    private PersonelDAO personelDAO;

    @Test
    public void personelListele() {
        List<Personel> personelList = personelDAO.personelListele();
        Assert.assertNotNull(personelList);
    }

    @Test
    public void personelGetir() {
        List<Personel> personelList = personelDAO.personelGetir(1L);
        Assert.assertNotNull(personelList);
    }
}