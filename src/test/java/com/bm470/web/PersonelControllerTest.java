package com.bm470.web;

import com.bm470.config.AppConfig;
import com.bm470.config.WebAppInitializer;
import com.bm470.config.WebConfig;
import com.bm470.model.Departman;
import com.bm470.model.Personel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class,WebConfig.class,WebAppInitializer.class})
@Transactional
@WebAppConfiguration
public  class PersonelControllerTest {


    @Autowired
    private PersonelController personelController;
    @Autowired
    private DepartmanController departmanController;

    @Test
    public void personelListele() {

        Model model = new Model() {
            @Override
            public Model addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public Model addAttribute(Object o) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
        String personelListele = personelController.personelListele(model);
        Boolean exist = personelListele.equals("personelListele");
        Assert.assertTrue(exist);
    }


    @Test
    public void personelKaydet() {
        String response = personelController.personelKaydet(
                null,"Mehmet","Akbulut",
                18512818914L,1L,40000L,
                "2022-05-15",4L
        );
        JSONObject jsonObject = JSONObject.fromObject(response);
        Boolean exist = (Boolean) jsonObject.get("exist");
        Assert.assertTrue(exist);

    }

    @Test
    public void personelEkle() {
        Model model = new Model() {
            @Override
            public Model addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public Model addAttribute(Object o) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
        String  viewFile =  personelController.personelEkle(model);
        Boolean exist = viewFile.equals("personelEkle");
        Assert.assertTrue(exist);
    }

    @Test
    public void personelGuncelle() {
        Model model = new Model() {
            @Override
            public Model addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public Model addAttribute(Object o) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
        String viewFile = personelController.personelGuncelle(7L,model);
        Boolean exist = viewFile.equals("personelGuncelle");
        Assert.assertNotNull(exist);

    }

    @Test
    public void personelSil() {
        String response = personelController.personelSil(7L);
        JSONObject jsonObject = JSONObject.fromObject(response);
        Boolean exist = (Boolean) jsonObject.get("exist");
        Assert.assertTrue(exist);
    }

    @Test
    public void personelGetir() {
        String response = personelController.personelGetir(4L);
        JSONObject jsonObject = JSONObject.fromObject(response);
        String personeller = (String) jsonObject.get("personeller");
        Boolean exist = (!personeller.equals("") && personeller != null);
        Assert.assertTrue(exist);
    }

    @Test
    public void personelTcKontrol() {
        String response = personelController.personelTcKontrol(18512818914L);
        JSONObject jsonObject = JSONObject.fromObject(response);
        Boolean exist = (Boolean) jsonObject.get("exist");
        Assert.assertTrue(exist);
    }
    @Test
    public void loadPersonel () {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.addParameter("columns[0][data]","personelId");
        MockHttpServletResponse responseMock = new MockHttpServletResponse();
        String response = personelController.loadPersonel(1,0,10,"",0,"asc",request,responseMock);
        JSONObject jsonObject = JSONObject.fromObject(response);
        JSONArray jsonArray =(JSONArray) jsonObject.get("data");
        Assert.assertTrue(jsonArray.size()>0);
    }
}