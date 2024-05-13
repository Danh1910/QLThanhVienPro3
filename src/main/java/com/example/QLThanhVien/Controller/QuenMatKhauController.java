package com.example.QLThanhVien.Controller;

import com.example.QLThanhVien.Enity.ThanhVienEntity;
import com.example.QLThanhVien.Enity.ThongTinSuDungEntity;
import com.example.QLThanhVien.Enity.XuLyViPhamEntity;
import com.example.QLThanhVien.Repository.ThanhVienRepository;
import com.example.QLThanhVien.Repository.ThietBiRepository;
import com.example.QLThanhVien.Repository.ThongTinSuDungRepository;
import com.example.QLThanhVien.Repository.XuLyViPhamRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.*;
@Controller
public class QuenMatKhauController {
    @Autowired
    private ThanhVienRepository tvRepository;

    @PostMapping("/forgot-password")
    public String sendResetPasswordEmail(@RequestParam String email, Model model) {
        // Lấy danh sách tất cả các thanh viên từ cơ sở dữ liệu
        Iterable<ThanhVienEntity> allThanhVien = tvRepository.findAll();

        // Duyệt qua từng thành viên để kiểm tra email
        for (ThanhVienEntity thanhVien : allThanhVien) {
            // Nếu email của thành viên hiện tại trùng với email được nhập từ form
            if (thanhVien.getEmail().equals(email)) {
                // Thực hiện gửi email reset mật khẩu ở đây

                // Gửi thông báo thành công về trang cho người dùng
                model.addAttribute("message", "Reset password email sent successfully!");
                return "ThanhVien.html"; // Trả về trang HTML thông báo gửi email thành công
            }
        }

        // Nếu không tìm thấy email trong cơ sở dữ liệu, trả về trang cho người dùng nhập lại email
        model.addAttribute("error", "Email not found in the database.");
        return "forgot-password"; // Trả về trang HTML chứa form quên mật khẩu và thông báo lỗi
    }
}
