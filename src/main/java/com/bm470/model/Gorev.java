package com.bm470.model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "gorev")
public class Gorev implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gorev_id", nullable = false)
    private Long gorevId;

    @Column(name = "gorev_adi", nullable = false)
    private String gorevAdi;

    @ManyToOne
    @JoinColumn(name = "departman_id",referencedColumnName = "departman_id")
    private Departman departman;

    @OneToMany(mappedBy = "gorev",fetch = FetchType.LAZY,cascade = CascadeType.MERGE,orphanRemoval = true)
    private List<Personel> personelList;

    public List<Personel> getPersonelList() {
        return personelList;
    }

    public void setPersonelList(List<Personel> personelList) {
        this.personelList = personelList;
    }

    public Departman getDepartman() {
        return departman;
    }

    public void setDepartman(Departman departman) {
        this.departman = departman;
    }


    public void setGorevId(Long id) {
        this.gorevId = id;
    }


    public Long getGorevId() {
        return gorevId;
    }

    public String getGorevAdi() {
        return gorevAdi;
    }

    public void setGorevAdi(String gorevAdi) {
        this.gorevAdi = gorevAdi;
    }
}