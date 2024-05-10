package com.example.QLThanhVien.Controller;

import com.example.QLThanhVien.Enity.ThanhVienEntity;
import com.example.QLThanhVien.Enity.ThietBiEntity;
import com.example.QLThanhVien.Enity.ThongTinSuDungEntity;
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

import java.util.Date;

@Controller
public class MuonTBController {
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

    @PutMapping("/MuonTB.html")
    public void save_dat_muon(@RequestParam(name = "MaTT") Integer maTT, @RequestParam(name = "MaTV") ThanhVienEntity maTV, @RequestParam(name = "MaTB") ThietBiEntity maTB, @RequestParam(name = "TGDatCho") Date tgianDatCho){
        ThongTinSuDungEntity thongTinSuDungEntity = new ThongTinSuDungEntity(maTT,maTV,maTB,tgianDatCho);

                ttsdRepository.save(thongTinSuDungEntity);
    }

}
