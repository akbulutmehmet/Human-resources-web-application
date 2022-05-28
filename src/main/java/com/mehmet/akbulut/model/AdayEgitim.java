package com.mehmet.akbulut.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "aday_egitim")
public class AdayEgitim implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "aday_egitim_id")
    private Long adayEgitimId;

    @Column(name = "egitim_turu",nullable = false)
    private String egitimTuru;

    @Column(name = "okul_adi",nullable = false)
    private String okulAdi;

    @Column(name = "bolum_adi",nullable = false)
    private String bolumAdi;

    @Column(name = "baslangic_tarihi",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date baslangicTarihi;

    @Column(name = "bitis_tarihi",nullable = true)
    @Temporal(TemporalType.DATE)
    private Date bitisTarihi;

    @Column(name = "file_name",nullable = true)
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "aday_id",referencedColumnName = "aday_id")
    private Aday aday;

    public Long getAdayEgitimId() {
        return adayEgitimId;
    }

    public void setAdayEgitimId(Long adayEgitimId) {
        this.adayEgitimId = adayEgitimId;
    }

    public String getEgitimTuru() {
        return egitimTuru;
    }

    public void setEgitimTuru(String egitimTuru) {
        this.egitimTuru = egitimTuru;
    }

    public String getOkulAdi() {
        return okulAdi;
    }

    public void setOkulAdi(String okulAdi) {
        this.okulAdi = okulAdi;
    }

    public String getBolumAdi() {
        return bolumAdi;
    }

    public void setBolumAdi(String bolumAdi) {
        this.bolumAdi = bolumAdi;
    }

    public Date getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public void setBaslangicTarihi(Date baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public Date getBitisTarihi() {
        return bitisTarihi;
    }

    public void setBitisTarihi(Date bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Aday getAday() {
        return aday;
    }

    public void setAday(Aday aday) {
        this.aday = aday;
    }
}
