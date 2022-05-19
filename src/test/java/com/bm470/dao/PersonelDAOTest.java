package com.bm470.dao;

import com.bm470.config.AppConfig;
import com.bm470.config.WebAppInitializer;
import com.bm470.config.WebConfig;
import com.bm470.model.Personel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



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
        Assert.assertTrue(personelList.size()>0);
    }

    @Test
    public void personelGetir() {
        List<Personel> personelList = personelDAO.personelGetir(4L);
        Assert.assertTrue(personelList.size()>0);
    }


    @Test
    public void loadPersonel () {
        List<Personel> personelList = personelDAO.loadPersonel(0,10,"","personelId","asc");
        Assert.assertTrue(personelList.size()>0);
    }

    @Test
    public void getTotalCount () {
        Long totalCount = personelDAO.getTotalCount("");
        Assert.assertTrue(totalCount>0);
    }
}