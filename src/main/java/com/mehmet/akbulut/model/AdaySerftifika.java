package com.mehmet.akbulut.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "aday_serftifika")
public class AdaySerftifika implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "aday_serftifika_id")
    private Long adaySertifikaId;

    @Column(name = "aday_serftifika_ad",nullable = false)
    private String adaySerftifikaAd;

    @Column(name = "aday_serftifika_url",nullable = false)
    private String adaySerftifikaUrl;


    @Column(name = "aday_serftifika_tarih",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date adaySerftifikaTarihi;



    @Column(name = "file_name",nullable = true)
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "aday_id",referencedColumnName = "aday_id")
    private Aday aday;

    public Long getAdaySertifikaId() {
        return adaySertifikaId;
    }

    public void setAdaySertifikaId(Long adaySertifikaId) {
        this.adaySertifikaId = adaySertifikaId;
    }

    public String getAdaySerftifikaAd() {
        return adaySerftifikaAd;
    }

    public void setAdaySerftifikaAd(String adaySerftifikaAd) {
        this.adaySerftifikaAd = adaySerftifikaAd;
    }

    public String getAdaySerftifikaUrl() {
        return adaySerftifikaUrl;
    }

    public void setAdaySerftifikaUrl(String adaySerftifikaUrl) {
        this.adaySerftifikaUrl = adaySerftifikaUrl;
    }

    public Date getAdaySerftifikaTarihi() {
        return adaySerftifikaTarihi;
    }

    public void setAdaySerftifikaTarihi(Date adaySerftifikaTarihi) {
        this.adaySerftifikaTarihi = adaySerftifikaTarihi;
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
