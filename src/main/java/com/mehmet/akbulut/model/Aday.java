package com.mehmet.akbulut.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "aday")
public class Aday implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="aday_id",nullable = false)
    private Long adayId;

    @Column(name="aday_ad",nullable = false)
    private String adayAd;

    @Column(name="aday_soyad",nullable = false)
    private String adaySoyad;

    @Transient
    private String adSoyad;


    @Column(name="aday_tc",nullable = false,unique = true)
    private Long adayTc;

    @Column(name="aday_cinsiyet",nullable = false,length = 1)
    private Long adayCinsiyet;

    @Column(name = "aday_eposta", nullable = false,unique = true)
    private String adayEposta;

    @Column(name = "aday_sifre", nullable = false)
    private String adaySifre;

    @Column(name="aday_dogum_tarihi",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date adayDogumTarihi;

    @OneToMany(mappedBy = "aday",fetch = FetchType.LAZY,cascade = CascadeType.MERGE,orphanRemoval = true)
    private List<AdayIsTecrube> adayIsTecrubeList;

    @OneToMany(mappedBy = "aday",fetch = FetchType.LAZY,cascade = CascadeType.MERGE,orphanRemoval = true)
    private List<AdayEgitim> adayEgitimList;

    @OneToMany(mappedBy = "aday",fetch = FetchType.LAZY,cascade = CascadeType.MERGE,orphanRemoval = true)
    private List<AdaySerftifika> adaySerftifikaList;

    public List<AdaySerftifika> getAdaySerftifikaList() {
        return adaySerftifikaList;
    }

    public void setAdaySerftifikaList(List<AdaySerftifika> adaySerftifikaList) {
        this.adaySerftifikaList = adaySerftifikaList;
    }

    public List<AdayEgitim> getAdayEgitimList() {
        return adayEgitimList;
    }

    public void setAdayEgitimList(List<AdayEgitim> adayEgitimList) {
        this.adayEgitimList = adayEgitimList;
    }

    public List<AdayIsTecrube> getAdayIsTecrubeList() {
        return adayIsTecrubeList;
    }

    public void setAdayIsTecrubeList(List<AdayIsTecrube> adayIsTecrubeList) {
        this.adayIsTecrubeList = adayIsTecrubeList;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getAdSoyad() {
        return adayAd + " " + adaySoyad;
    }


    public String getAdaySoyad() {
        return adaySoyad;
    }

    public void setAdaySoyad(String adaySoyad) {
        this.adaySoyad = adaySoyad;
    }

    public void setAdayDogumTarihi(Date adayDogumTarihi) {
        this.adayDogumTarihi = adayDogumTarihi;
    }

    public Date getAdayDogumTarihi() {
        return adayDogumTarihi;
    }

    public void setAdayTc(Long adayTc) {
        this.adayTc = adayTc;
    }

    public Long getAdayTc() {
        return adayTc;
    }

    public void setAdaySifre(String adaySifre) {
        this.adaySifre = adaySifre;
    }

    public String getAdaySifre() {
        return adaySifre;
    }

    public void setAdayId(Long adayId) {
        this.adayId = adayId;
    }

    public Long getAdayId() {
        return adayId;
    }

    public void setAdayEposta(String adayEposta) {
        this.adayEposta = adayEposta;
    }

    public String getAdayEposta() {
        return adayEposta;
    }

    public void setAdayCinsiyet(Long adayCinsiyet) {
        this.adayCinsiyet = adayCinsiyet;
    }

    public Long getAdayCinsiyet() {
        return adayCinsiyet;
    }

    public void setAdayAd(String adayAd) {
        this.adayAd = adayAd;
    }

    public String getAdayAd() {
        return adayAd;
    }
}
