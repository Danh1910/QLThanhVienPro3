package com.example.QLThanhVien.Enity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "thietbi")

public class ThietBiEntity {

    @Id
    @Column ( name = "MaTB")
    private Integer MaTB;

    @Column ( name = "TenTB")
    private String TenTB;

    @Column ( name = "mo_tatb")
    private String MoTaTB;


    public ThietBiEntity(Integer MaTB, String TenTB, String MoTaTB) {
        this.MaTB = MaTB;
        this.TenTB = TenTB;
        this.MoTaTB = MoTaTB;
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

    public void setMoTaTB(String MoTaTB) {
        this.MoTaTB = MoTaTB;
    }


}
