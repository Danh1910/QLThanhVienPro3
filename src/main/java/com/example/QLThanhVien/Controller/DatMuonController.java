package com.example.QLThanhVien.Controller;

import com.example.QLThanhVien.Enity.ThanhVienEntity;
import com.example.QLThanhVien.Enity.ThietBiEntity;
import com.example.QLThanhVien.Enity.ThongTinSuDungEntity;
import com.example.QLThanhVien.Enity.XuLyViPhamEntity;
import com.example.QLThanhVien.Repository.ThietBiRepository;

import org.hibernate.mapping.List;
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
import java.util.Date;

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

        ThongTinSuDungEntity thongTinSuDungEntity = new ThongTinSuDungEntity(MaTV,MaTB,date);
        ttsdRepository.save(thongTinSuDungEntity);
    }

    // public ThongTinSuDungEntity CheckDatCho(List<ThongTinSuDungEntity> list, int idtb, Date datcho ){
    //     for (ThongTinSuDungEntity temp: list){
    //         if (temp.getMaTB().getMaTB() != null) {
    //             if (temp.getTGDatCho() != null ) {
    //                 return temp;
    //             }
    //         }
    //     }

    //     return null;
    // }

}
