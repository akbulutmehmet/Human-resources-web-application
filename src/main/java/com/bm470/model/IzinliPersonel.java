package com.bm470.model;

import javax.persistence.*;

@Entity
@Table(name = "izinli_personel")
public class IzinliPersonel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long izinliPersonelId;

    @Column(name = "izin_baslangic_tarihi", nullable = false)
    private String izinBaslangicTarihi;

    @Column(name = "izin_bitis_tarihi", nullable = false)
    private String izinBitisTarihi;


    @ManyToOne
    @JoinColumn(name = "personel_id", referencedColumnName = "personel_id")
    private Personel personel;

    public String getIzinBaslangicTarihi() {
        return izinBaslangicTarihi;
    }

    public void setIzinBaslangicTarihi() {
        this.izinBaslangicTarihi = izinBaslangicTarihi;
    }

    public String getIzinBitisTarihi() {
        return izinBitisTarihi;
    }

    public void setIzinBitisTarihi() {
        this.izinBitisTarihi = izinBaslangicTarihi;
    }

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }
}
