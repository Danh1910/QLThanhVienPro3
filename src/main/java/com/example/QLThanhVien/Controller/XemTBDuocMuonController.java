package com.example.QLThanhVien.Controller;

import com.example.QLThanhVien.Enity.ThongTinSuDungEntity;
import com.example.QLThanhVien.Enity.XuLyViPhamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.QLThanhVien.Repository.ThanhVienRepository;
import com.example.QLThanhVien.Repository.ThongTinSuDungRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class XemTBDuocMuonController {
    @Autowired
    private ThanhVienRepository tvRepository;
    @Autowired
    private ThongTinSuDungRepository ttsdRepository;


    @RequestMapping("/XemTBDuocMuon.html")
    public String action(Model model){
        System.out.println("Thong tin cho xem vi phạm");

        return "XemTBDuocMuon.html";
    }

    @RequestMapping("/XemTBDaMuon.html")
    public String action1(Model model){
        System.out.println("Thong tin cho xem vi phạm");

        return "XemTBDaMuon.html";
    }

    @GetMapping("/XemTBDuocMuon.html")
    public String getViPham(@RequestParam Integer maTV, Model model) {
        // Tìm thông tin của thành viên dựa trên mã thành viên
        List<ThongTinSuDungEntity> xlvp = ttsdRepository.listmuon(maTV);

        // Kiểm tra xem thành viên có tồn tại không

        model.addAttribute("data", xlvp); // Truyền thông tin thành viên vào model

        return "XemTBDuocMuon"; // Trả về trang thông tin thành viên

    }

    @GetMapping("/XemTBDaMuon.html")
    public String getViPham1(@RequestParam Integer maTV, Model model) {
        // Tìm thông tin của thành viên dựa trên mã thành viên
        List<ThongTinSuDungEntity> xlvp = ttsdRepository.listmuon(maTV);

        // Kiểm tra xem thành viên có tồn tại không

        model.addAttribute("data1", xlvp); // Truyền thông tin thành viên vào model

        return "XemTBDaMuon"; // Trả về trang thông tin thành viên

    }
}
