package com.bm470.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "personel")
public class Personel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="personel_id",nullable = false)
    private Long personelId;

    @Column(name="personel_ad",nullable = false)
    private String personelAd;

    @Column(name="personel_soyad",nullable = false)
    private String personelSoyad;

    @Transient
    private String personelAdSoyad;

    @Column(name="personel_tc",nullable = false)
    private Long personelTc;

    @Column(name="personel_cinsiyet",nullable = false)
    private String personelCinsiyet;


    @Column(name="personel_maas",nullable = false)
    private Long personelMaas;

    @Column(name="is_baslangic_tarihi",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date isBaslangicTarihi;



    @ManyToOne
    @JoinColumn(name = "gorev_id",referencedColumnName = "gorev_id")
    private Gorev gorev;


    public String getPersonelAdSoyad() {
        return getPersonelAd() + " " + getPersonelSoyad();
    }

    public void setPersonelAdSoyad(String personelAdSoyad) {
        this.personelAdSoyad = personelAdSoyad;
    }

    public void setPersonelId(Long id) {
        this.personelId = id;
    }

    public Long getPersonelId() {
        return personelId;
    }

    public void setPersonelAd(String personelAd) {
        this.personelAd = personelAd;
    }

    public String getPersonelAd() {
        return personelAd;
    }

    public void setPersonelSoyad(String personelSoyad) {
        this.personelSoyad = personelSoyad;
    }

    public String getPersonelSoyad() {
        return personelSoyad;
    }

    public void setPersonelTc(Long personelTc) {
        this.personelTc = personelTc;
    }

    public Long getPersonelTc() {
        return personelTc;
    }

    public void setPersonelCinsiyet(String personelCinsiyet) {
        this.personelCinsiyet = personelCinsiyet;
    }

    public String getPersonelCinsiyet() {
        return personelCinsiyet;
    }


    public Long getPersonelMaas() {
        return this.personelMaas;
    }

    public void setPersonelMaas(Long personelMaas) {
        this.personelMaas = personelMaas;
    }

    public void setIsBaslangicTarihi(Date isBaslangicTarihi) {
        this.isBaslangicTarihi = isBaslangicTarihi;
    }

    public Date getIsBaslangicTarihi() {
        return isBaslangicTarihi;
    }




    public Gorev getGorev() {
        return gorev;
    }

    public void setGorev(Gorev gorev) {
        this.gorev = gorev;
    }

}