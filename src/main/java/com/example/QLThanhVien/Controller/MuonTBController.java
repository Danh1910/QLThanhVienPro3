package com.example.QLThanhVien.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.QLThanhVien.Enity.ThanhVienEnity;
import com.example.QLThanhVien.Repository.ThanhVienRepository;
import com.example.QLThanhVien.Repository.ThongTinSuDungRepository;

@Controller
public class MuonTBController {
    @Autowired
    private ThanhVienRepository tvRepository;
    @Autowired
    private ThongTinSuDungRepository ttsdRepository;


    @RequestMapping("/MuonTB.html")
    public String action(Model model){
        System.out.println("Thong tin cho bang muon thiet bi");

        return "MuonTB.html";
    }
}
