package com.example.QLThanhVien.Enity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;


@Data
@Entity
@Table(name = "thongtinsd")
public class ThongTinSuDungEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTT")
    private int MaTT;


    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="MaTV",nullable=false,foreignKey=@ForeignKey(name="fk_ThongTinSuDungDTO_ThanhVienDTO"))
    private ThanhVienEnity MaTV;



    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="MaTB",nullable=true,foreignKey=@ForeignKey(name="fk_ThongTinSuDungDTO_ThietBiDTO"))
    private ThietBiEntity MaTB;



    @Column(name = "TGVao")
    private Date TGVao;


    @Column(name = "TGMuon")
    private Date TGMuon;


    @Column(name = "TGTra")
    private Date TGTra;



    public ThongTinSuDungEntity() {
    }


    public ThongTinSuDungEntity(int MaTT, ThanhVienEnity MaTV, ThietBiEntity MaTB, Date TGVao, Date TGMuon, Date TGTra) {
        this.MaTT = MaTT;
        this.MaTV = MaTV;
        this.MaTB = MaTB;
        this.TGVao = TGVao;
        this.TGMuon = TGMuon;
        this.TGTra = TGTra;
    }



    public int getMaTT() {
        return MaTT;
    }

    public ThanhVienEnity getMaTV() {
        return MaTV;
    }

    public ThietBiEntity getMaTB() {
        return MaTB;
    }

    public Date getTGVao() {
        return TGVao;
    }

    public Date getTGMuon() {
        return TGMuon;
    }

    public Date getTGTra() {
        return TGTra;
    }

    public void setMaTT(int maTT) {
        MaTT = maTT;
    }

    public void setMaTV(ThanhVienEnity maTV) {
        MaTV = maTV;
    }

    public void setMaTB(ThietBiEntity maTB) {
        MaTB = maTB;
    }

    public void setTGVao(Date TGVao) {
        this.TGVao = TGVao;
    }

    public void setTGMuon(Date TGMuon) {
        this.TGMuon = TGMuon;
    }

    public void setTGTra(Date TGTra) {
        this.TGTra = TGTra;
    }
}


