package com.bm470.model;


import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name = "insan_kaynaklari")
public class InsanKaynaklari implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ik_id", nullable = false)
    private Long ikId;

    @Column(name = "ik_ad", nullable = false)
    private String ikAd;

    @Column(name = "ik_soyad", nullable = false)
    private String ikSoyad;

    @Transient
    private String adSoyad;

    @Column(name = "ik_eposta", nullable = false,unique = true)
    private String ikEposta;

    @Column(name = "ik_sifre", nullable = false)
    private String ikSifre;

    public String getAdSoyad () {
        adSoyad = getIkAd() + " " +getIkSoyad();
        return adSoyad;
    }

    public void setIkId(Long ikId) {
        this.ikId = ikId;
    }

    public Long getIkId() {
        return ikId;
    }


    public void setIkAd(String ikAd) {
        this.ikAd = ikAd;
    }

    public String getIkAd() {
        return ikAd;
    }

    public String getIkSoyad() {
        return ikSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getIkEposta() {
        return ikEposta;
    }

    public void setIkEposta(String ikEposta) {
        this.ikEposta = ikEposta;
    }

    public String getIkSifre() {
        return ikSifre;
    }

    public void setIkSifre(String ikSifre) {
        this.ikSifre = ikSifre;
    }

    public void setIkSoyad(String ikSoyad) {
        this.ikSoyad = ikSoyad;
    }

}