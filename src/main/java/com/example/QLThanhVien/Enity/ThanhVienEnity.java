/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.QLThanhVien.Enity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "thanhvien")
public class ThanhVienEnity {
    
    @Id
    @Column(name = "MaTV")
    private int MaTV;

    @Column(name = "HoTen")
    private String HoTen;

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
    public ThanhVienEnity() {
    }
    
    public ThanhVienEnity(int MaTV, String HoTen, String Khoa, String Nganh, String SDT) {
        this.MaTV = MaTV;
        this.HoTen = HoTen;
        this.Khoa = Khoa;
        this.Nganh = Nganh;
        this.SDT = SDT;
    }
    public ThanhVienEnity(String HoTen, String Khoa, String Nganh, String SDT) {
        this.HoTen = HoTen;
        this.Khoa = Khoa;
        this.Nganh = Nganh;
        this.SDT = SDT;
    }

    public ThanhVienEnity(int MaTV, String HoTen, String Khoa, String Nganh, String SDT, String Password, String Email) {
        this.MaTV = MaTV;
        this.HoTen = HoTen;
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
    
    public String getHoTen() {
        return HoTen;
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

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
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
