/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.QLThanhVien.Enity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
@Entity
@Table(name = "thanhvien")
public class ThanhVienEntity {
    
    @Id
    @Column(name = "MaTV")
    private int MaTV;

    @Column(name = "Ten")
    private String Ten;

    @Column(name = "Khoa")
    private String Khoa;

    @Column(name = "Nganh")
    private String Nganh;

    @Column(name = "SDT")
    private String SDT;
    
    @Column(name = "Password")
    private String Password;
    
    @Column(name = "Email")
    private String Email;
    
//    @OneToMany(fetch=FetchType.LAZY, mappedBy= "thanhvien")
//    private ArrayList<ThongTinSuDungEnity> ttsd = new ArrayList<>();
//    
    public ThanhVienEntity() {
    }
    
    public ThanhVienEntity(int MaTV, String Ten, String Khoa, String Nganh, String SDT) {
        this.MaTV = MaTV;
        this.Ten = Ten;
        this.Khoa = Khoa;
        this.Nganh = Nganh;
        this.SDT = SDT;
    }
    public ThanhVienEntity(String Ten, String Khoa, String Nganh, String SDT) {
        this.Ten = Ten;
        this.Khoa = Khoa;
        this.Nganh = Nganh;
        this.SDT = SDT;
    }

    public ThanhVienEntity(int MaTV, String Ten, String Khoa, String Nganh, String SDT, String Email,String Password) {
        this.MaTV = MaTV;
        this.Ten = Ten;
        this.Khoa = Khoa;
        this.Nganh = Nganh;
        this.SDT = SDT;
        this.Password = Password;
        this.Email = Email;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    public String getTen() {
        return Ten;
    }

    public int getMaTV() {
        return MaTV;
    }

    public String getKhoa() {
        return Khoa;
    }

    public String getNganh() {
        return Nganh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public void setKhoa(String Khoa) {
        this.Khoa = Khoa;
    }

    public void setMaTV(int MaTV) {
        this.MaTV = MaTV;
    }

    public void setNganh(String Nganh) {
        this.Nganh = Nganh;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }    

//    public void setTtsd(ArrayList<ThongTinSuDungEnity> ttsd) {
//        this.ttsd = ttsd;
//    }
//
//    public ArrayList<ThongTinSuDungEnity> getTtsd() {
//        return ttsd;
//    }
    
}
