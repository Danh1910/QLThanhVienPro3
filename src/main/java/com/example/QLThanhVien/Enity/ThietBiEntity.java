package com.example.QLThanhVien.Enity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "thietbi")

public class ThietBiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column ( name = "MaTB")
    private Integer MaTB;

    @Column ( name = "TenTB")
    private String TenTB;

    @Column ( name = "MoTaTB")
    private String MoTaTB;


    public ThietBiEntity(Integer MaTB, String TenTB, String MoTa) {
        this.MaTB = MaTB;
        this.TenTB = TenTB;
        this.MoTaTB = MoTa;
    }

    public ThietBiEntity() {
    }



    public Integer getMaTB() {
        return MaTB;
    }

    public void setMaTB(Integer MaTB) {
        this.MaTB = MaTB;
    }

    public String getTenTB() {
        return TenTB;
    }

    public void setTenTB(String TenTB) {
        this.TenTB = TenTB;
    }

    public String getMoTaTB() {
        return MoTaTB;
    }

    public void setMoTaTB(String MoTa) {
        this.MoTaTB = MoTa;
    }


}
