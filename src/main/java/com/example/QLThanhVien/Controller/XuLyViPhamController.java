package com.example.QLThanhVien.Controller;

import com.example.QLThanhVien.Enity.ThietBiEntity;
import com.example.QLThanhVien.Enity.XuLyViPhamEntity;
import com.example.QLThanhVien.Repository.XuLyViPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class XuLyViPhamController {
    @Autowired
    private XuLyViPhamRepository xlvpRepository;
    @RequestMapping("/XuLyViPham.html")
    public String LoadData(Model model){


        Iterable<XuLyViPhamEntity> list= xlvpRepository.findAll();

        model.addAttribute("data",list);


        return "XuLyViPham.html";
    }
}
