package com.bm470.model;

import javax.persistence.*;
import java.util.Date;
import java.util.zip.DataFormatException;

@Entity
@Table(name = "izinli_personel")
public class IzinliPersonel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long izinliPersonelId;

    @Column(name = "izin_baslangic_tarihi", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date izinBaslangicTarihi;

    @Column(name = "izin_bitis_tarihi", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date izinBitisTarihi;


    @ManyToOne
    @JoinColumn(name = "personel_id", referencedColumnName = "personel_id")
    private Personel personel;

    public long getIzinliPersonelId() {
        return izinliPersonelId;
    }

    public void setIzinliPersonelId(long izinliPersonelId) {
        this.izinliPersonelId = izinliPersonelId;
    }

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }

    public Date getIzinBitisTarihi() {
        return izinBitisTarihi;
    }

    public void setIzinBitisTarihi(Date izinBitisTarihi) {
        this.izinBitisTarihi = izinBitisTarihi;
    }

    public Date getIzinBaslangicTarihi() {
        return izinBaslangicTarihi;
    }

    public void setIzinBaslangicTarihi(Date izinBaslangicTarihi) {
        this.izinBaslangicTarihi = izinBaslangicTarihi;
    }
}
