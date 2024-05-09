package com.example.QLThanhVien.Enity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "thietbi")

public class ThietBiEntity {

    @Getter
    @Setter
    @Id
    @Column ( name = "MaTB")
    private Integer MaTB;

    @Getter
    @Setter
    @Column ( name = "TenTB")
    private String TenTB;

    @Getter
    @Setter
    @Column ( name = "mo_tatb")
    private String MoTaTB;


    public ThietBiEntity(Integer MaTB, String TenTB, String MoTaTB) {
        this.MaTB = MaTB;
        this.TenTB = TenTB;
        this.MoTaTB = MoTaTB;
    }

    public ThietBiEntity() {
    }




}
