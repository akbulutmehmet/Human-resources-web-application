package com.bm470.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "departman")
public class Departman implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="departman_id", nullable = false)
    private Long departmanId;

    @Column(name="departman_adi", nullable = false)
    private String departmanAdi;


    @OneToMany(mappedBy = "departman",fetch = FetchType.LAZY,cascade = CascadeType.MERGE,orphanRemoval = true)
    private List<Gorev> gorevList;

    public List<Gorev> getGorevList() {
        return gorevList;
    }

    public void setGorevList(List<Gorev> gorevList) {
        this.gorevList = gorevList;
    }


    public Long getDepartmanId() {
        return departmanId;
    }

    public void setDepartmanId(Long departmanId) {
        this.departmanId = departmanId;
    }

    public String getDepartmanAdi() {
        return departmanAdi;
    }

    public void setDepartmanAdi(String departmanAdi) {
        this.departmanAdi = departmanAdi;
    }


}