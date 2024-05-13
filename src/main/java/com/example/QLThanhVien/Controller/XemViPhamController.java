package com.example.QLThanhVien.Controller;

import com.example.QLThanhVien.Enity.ThanhVienEntity;
import com.example.QLThanhVien.Enity.XuLyViPhamEntity;
import com.example.QLThanhVien.Repository.XuLyViPhamRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.QLThanhVien.Repository.ThanhVienRepository;
import com.example.QLThanhVien.Repository.ThongTinSuDungRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class XemViPhamController {
    @Autowired
    private ThanhVienRepository tvRepository;
    @Autowired
    private ThongTinSuDungRepository ttsdRepository;
    @Autowired
    private XuLyViPhamRepository xlvpRepository;


    @RequestMapping("/XemViPham.html")
    public String action(Model model){
        System.out.println("Thong tin cho xem vi phạm");

        return "XemViPham.html";
    }

    @GetMapping("/XemViPham.html")
    public String getViPham(@RequestParam Integer maTV, Model model) {
        // Tìm thông tin của thành viên dựa trên mã thành viên
        List<XuLyViPhamEntity> xlvp = xlvpRepository.findByMaTV(maTV);

        // Kiểm tra xem thành viên có tồn tại không

            model.addAttribute("data", xlvp); // Truyền thông tin thành viên vào model

            return "XemViPham"; // Trả về trang thông tin thành viên

    }
}
