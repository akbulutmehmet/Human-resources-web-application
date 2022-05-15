package com.bm470.web;

import com.bm470.config.AppConfig;
import com.bm470.config.WebAppInitializer;
import com.bm470.config.WebConfig;
import com.bm470.model.Departman;
import com.bm470.model.Personel;
import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppInitializer.class, AppConfig.class,
        WebConfig.class})
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
        Boolean exist = true;
        if(personelListele.equals("") || personelListele==null) {
            exist = false;
        }
        Assert.assertTrue(exist);
    }


    @Test
    public void personelKaydet() {
        String response = personelController.personelKaydet(
                null,"Mehmet","Akbulut",
                1234L,"erkek",40000L,
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
        String  exist =  departmanController.departmanListele(model);
        Assert.assertNotNull(exist);
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
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("personelId", String.valueOf(1L));
        String exist = personelController.personelGuncelle(Long.parseLong(request.getParameter("personelId")),model);
        Assert.assertNotNull(exist);

    }

    @Test
    public void personelSil() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("personelId", String.valueOf(1L));
        String exist = personelController.personelSil(Long.parseLong(request.getParameter("personelId")));
        Assert.assertNotNull(exist);

    }

    @Test
    public void personelGetir() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("personelId", String.valueOf(1L));
        String exist = personelController.personelGetir(Long.parseLong(request.getParameter("personelId")));
        Assert.assertNotNull(exist);

    }
}