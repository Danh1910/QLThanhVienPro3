package com.example.QLThanhVien.Controller;

import com.example.QLThanhVien.Enity.ThanhVienEntity;
import com.example.QLThanhVien.Enity.ThietBiEntity;
import com.example.QLThanhVien.Enity.ThongTinSuDungEntity;
import com.example.QLThanhVien.Enity.XuLyViPhamEntity;
import com.example.QLThanhVien.Repository.ThietBiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.QLThanhVien.Repository.ThanhVienRepository;
import com.example.QLThanhVien.Repository.ThongTinSuDungRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DatMuonController {
    @Autowired
    private ThanhVienRepository tvRepository;

    @Autowired
    private ThietBiRepository thietBiRepository;

    @Autowired
    private ThongTinSuDungRepository ttsdRepository;


    @RequestMapping("/MuonTB.html")
    public String LoadData(Model model){

        model.addAttribute("message", "Đặt mượn thiết bị");

        Iterable<ThietBiEntity> list= thietBiRepository.findAll();

        model.addAttribute("data",list);

        return "MuonTB.html";
    }

    @PostMapping("/MuonTB.html")
    public void addThongTin(@RequestParam(name = "MaTV") ThanhVienEntity MaTV, @RequestParam(name = "MaTB") ThietBiEntity MaTB, @RequestParam(name = "TGianDatCho") String TGianDatCho){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = format.parse(TGianDatCho);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        if (CheckDatCho(MaTB, date)) {

            ThongTinSuDungEntity thongTinSuDungEntity = new ThongTinSuDungEntity(MaTV,MaTB,date);
            ttsdRepository.save(thongTinSuDungEntity);
        }
    }

    public boolean CheckDatCho(ThietBiEntity MaTB, Date TGianDatCho){
        // Lấy danh sách thông tin sử dụng từ repository hoặc cơ sở dữ liệu
        Iterable<ThongTinSuDungEntity> list = ttsdRepository.findAll();
    
        // Lặp qua danh sách để kiểm tra xem có thiết bị nào đã được đặt chỗ trong khoảng thời gian này chưa
        for (ThongTinSuDungEntity temp: list){
            // Kiểm tra xem thiết bị đã được đặt chỗ chưa và có trùng với MaTB không
            if (temp.getMaTB() == MaTB && temp.getTGDatCho() != null) {
                // Nếu có thì kiểm tra xem TGianDatCho có trùng với TGianDatCho của đặt chỗ hiện tại không
                if (temp.getTGDatCho().equals(TGianDatCho)) {
                    // Nếu có thì thông báo là không thể đặt thêm chỗ và trả về false
                    System.out.println("Không thể đặt thêm chỗ vì thiết bị đã được đặt chỗ trong khoảng thời gian này.");
                    return false;
                }
            }
        }
    
        // Nếu không có thông tin sử dụng nào trùng với MaTB và TGianDatCho thì có thể đặt chỗ, trả về true
        return true;
    }

}
