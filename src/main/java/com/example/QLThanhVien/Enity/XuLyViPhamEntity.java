package com.example.QLThanhVien.Enity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Entity
@Table(name="xuly")
public class XuLyViPhamEntity {

    @Getter
    @Setter
    @Id
    @Column(name="MaXL")
    private Integer MaXL;

    @Getter
    @Setter
    @Column(name="MaTV")
    private Integer MaTV;

    @Getter
    @Setter
    @Column(name="hinh_thucxl")
    private String hinh_thucxl;

    @Getter
    @Setter
    @Column(name="so_tien")
    private Integer so_tien;

    @Getter
    @Setter
    @Column(name="NgayXL")
    private Date NgayXL;

    @Getter
    @Setter
    @Column(name="trang_thaixl")
    private Integer trang_thaixl;
    
    public XuLyViPhamEntity(Integer MaXL, Integer MaTV, String hinh_thucxl, Integer so_tien, Date NgayXL, Integer trang_thaixl){
        this.MaXL = MaXL;
        this.MaTV = MaTV;
        this.hinh_thucxl = hinh_thucxl;
        this.so_tien = so_tien;
        this.NgayXL = NgayXL;
        this.trang_thaixl = trang_thaixl;
    }

    public XuLyViPhamEntity() {
    }
}
