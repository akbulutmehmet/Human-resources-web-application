package com.mehmet.akbulut.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "aday_is_tecrube")
public class AdayIsTecrube implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "aday_is_tecrube_id")
    private Long adayIsTecrubeId;
    @Column(name = "isyeri_adi")
    private String isyeriAdi;
    @Column(name = "gorev_adi")
    private String gorevAdi;

    @Column(name = "gorevli_adi",nullable = false)
    private String gorevliAdi;

    @Column(name = "gorevli_unvan",nullable = false)
    private String gorevliUnvan;

    @Column(name = "gorevli_telefon",nullable = false)
    private Long gorevliTelefon;

    @Column(name = "is_baslangic_tarihi")
    @Temporal(TemporalType.DATE)
    private Date isBaslangicTarihi;

    @Column(name = "is_bitis_tarihi",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date isBitisTarihi;


    @ManyToOne
    @JoinColumn(name = "aday_id",referencedColumnName = "aday_id")
    private Aday aday;

    public Aday getAday() {
        return aday;
    }

    public void setAdayIsTecrubeId(Long adayIsTecrubeId) {
        this.adayIsTecrubeId = adayIsTecrubeId;
    }

    public void setIsyeriAdi(String isyeriAdi) {
        this.isyeriAdi = isyeriAdi;
    }

    public void setGorevAdi(String gorevAdi) {
        this.gorevAdi = gorevAdi;
    }

    public void setGorevliAdi(String gorevliAdi) {
        this.gorevliAdi = gorevliAdi;
    }

    public void setGorevliUnvan(String gorevliUnvan) {
        this.gorevliUnvan = gorevliUnvan;
    }



    public void setAday(Aday aday) {
        this.aday = aday;
    }

    public void setIsBaslangicTarihi(Date isBaslangicTarihi) {
        this.isBaslangicTarihi = isBaslangicTarihi;
    }

    public void setIsBitisTarihi(Date isBitisTarihi) {
        this.isBitisTarihi = isBitisTarihi;
    }

    public Long getAdayIsTecrubeId() {
        return adayIsTecrubeId;
    }

    public String getIsyeriAdi() {
        return isyeriAdi;
    }

    public String getGorevAdi() {
        return gorevAdi;
    }

    public String getGorevliAdi() {
        return gorevliAdi;
    }

    public String getGorevliUnvan() {
        return gorevliUnvan;
    }

    public Long getGorevliTelefon() {
        return gorevliTelefon;
    }

    public void setGorevliTelefon(Long gorevliTelefon) {
        this.gorevliTelefon = gorevliTelefon;
    }

    public Date getIsBaslangicTarihi() {
        return isBaslangicTarihi;
    }

    public Date getIsBitisTarihi() {
        return isBitisTarihi;
    }
}
