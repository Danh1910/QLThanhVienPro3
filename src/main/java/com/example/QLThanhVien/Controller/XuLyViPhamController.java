package com.example.QLThanhVien.Controller;

import com.example.QLThanhVien.Enity.ThietBiEntity;
import com.example.QLThanhVien.Enity.XuLyViPhamEntity;
import com.example.QLThanhVien.Repository.XuLyViPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

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
    @PostMapping("/XuLyViPham.html")
    public void addDevice(@RequestParam(name = "MaTV") Integer MaTV, @RequestParam(name = "HTXL") String HTXL, @RequestParam(name = "SoTien") Integer SoTien, @RequestParam(name = "NgayXL") String NgayXL, @RequestParam(name = "TrangThaiXL") Integer TrangThaiXL){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = format.parse(NgayXL);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        XuLyViPhamEntity xlvpEntity = new XuLyViPhamEntity(MaTV,HTXL,SoTien,date,TrangThaiXL);
        xlvpRepository.save(xlvpEntity);
    }
}
