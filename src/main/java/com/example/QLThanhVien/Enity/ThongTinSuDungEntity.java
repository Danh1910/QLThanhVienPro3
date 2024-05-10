package com.example.QLThanhVien.Enity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;


@Data
@Entity
@Table(name = "thongtinsd")
public class ThongTinSuDungEntity {


    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTT")
    private int MaTT;


    @Setter
    @Getter
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="MaTV",nullable=false,foreignKey=@ForeignKey(name="fk_ThongTinSuDungDTO_ThanhVienDTO"))
    private ThanhVienEntity MaTV;



    @Setter
    @Getter
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="MaTB",nullable=true,foreignKey=@ForeignKey(name="fk_ThongTinSuDungDTO_ThietBiDTO"))
    private ThietBiEntity MaTB;



    @Setter
    @Getter
    @Column(name = "TGVao")
    private Date TGVao;


    @Setter
    @Getter
    @Column(name = "TGMuon")
    private Date TGMuon;


    @Setter
    @Getter
    @Column(name = "TGTra")
    private Date TGTra;

    @Column(name = "TGDatCho")
    private Date TGDatCho;



    public ThongTinSuDungEntity() {
    }


    public ThongTinSuDungEntity(int MaTT, ThanhVienEntity MaTV, ThietBiEntity MaTB, Date TGVao, Date TGMuon, Date TGTra, Date TGDatCho) {
        this.MaTT = MaTT;
        this.MaTV = MaTV;
        this.MaTB = MaTB;
        this.TGVao = TGVao;
        this.TGMuon = TGMuon;
        this.TGTra = TGTra;
        this.TGDatCho = TGDatCho;
    }

    public ThongTinSuDungEntity(int MaTT, ThanhVienEntity MaTV, ThietBiEntity MaTB, Date TGDatCho){
        this.MaTT = MaTT;
        this. MaTV = MaTV;
        this.MaTB = MaTB;
        this.TGDatCho = TGDatCho;
    }

    


}


