package com.example.QLThanhVien.Controller;

import com.example.QLThanhVien.Enity.ThanhVienEntity;
import com.example.QLThanhVien.Repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
    @Autowired
    private ThanhVienRepository tvRepository;

    @PostMapping("/login.html")
    public String login(@RequestParam Integer maTV, @RequestParam String password, Model model) {
        // Tìm thông tin của thành viên dựa trên mã thành viên
        ThanhVienEntity user = tvRepository.findById(Long.valueOf(maTV)).orElse(null);

        // Kiểm tra nếu người dùng tồn tại và mật khẩu nhập đúng
        if (user != null && user.getPassword().equals(password)) {
            // Đăng nhập thành công
            model.addAttribute("thanhVien", user); // Truyền thông tin của thành viên vào model
            return "ThongTinTV.html"; // Chuyển hướng đến trang thông tin thành viên
        } else {
            // Đăng nhập thất bại
            model.addAttribute("error", "Mã thành viên hoặc mật khẩu không đúng");
            return "redirect:/login.html"; // Chuyển hướng lại trang đăng nhập với thông báo lỗi
        }
    }

}