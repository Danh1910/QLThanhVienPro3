package com.example.QLThanhVien.Controller;


import com.example.QLThanhVien.Enity.ThanhVienEntity;
import com.example.QLThanhVien.Repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Locale;


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

    @PostMapping("/DangKy.html")
    public void addMember(@RequestParam(name = "Ten") String tenTV,
                          @RequestParam(name = "Khoa") String khoa,
                          @RequestParam(name = "Nganh") String nganh,
                          @RequestParam(name = "SDT") String sdt,
                          @RequestParam(name = "Email") String email,
                          @RequestParam(name = "Password") String password) {
        Long count = tvRepository.countAll();
        LocalDate currentDate = LocalDate.now();
        String year = String.valueOf(currentDate.getYear()).substring(2);
        String khoaCode = "00";
        String idTV;
        switch (khoa.toUpperCase(Locale.ROOT)) {
            case "SP KHXH":
                switch (nganh.toUpperCase(Locale.ROOT)) {
                    case "ĐỊA":
                        khoaCode = "11";
                        break;
                    case "SỬ":
                        khoaCode = "10";
                        break;
                    case "VĂN":
                        khoaCode = "09";
                        break;
                    default:
                        khoaCode = "00";
                        break;
                }
                break;
            case "SP KHTN":
                switch (nganh.toUpperCase(Locale.ROOT)) {
                    case "LÍ":
                        khoaCode = "02";
                        break;
                    case "HÓA":
                        khoaCode = "03";
                        break;
                    case "SINH":
                        khoaCode = "04";
                        break;
                    default:
                        khoaCode = "00";
                        break;
                }
                break;
            case "NGOẠI NGỮ":
                switch (nganh.toUpperCase(Locale.ROOT)) {
                    case "ANH":
                        khoaCode = "13";
                        break;
                    case "NNA":
                        khoaCode = "38";
                        break;
                    default:
                        khoaCode = "00";
                        break;
                }
                break;
            case "QTKD":
                khoaCode = "55";
                break;
            case "QLGD":
                switch (nganh.toUpperCase(Locale.ROOT)) {
                    case "TLH":
                        khoaCode = "53";
                        break;
                    default:
                        khoaCode = "00";
                        break;
                }
                break;
            case "TOÁN UD":
                switch (nganh.toUpperCase(Locale.ROOT)) {
                    case "TOÁN":
                        khoaCode = "48";
                        break;
                    default:
                        khoaCode = "00";
                        break;
                }
                break;
            case "CNTT":
                switch (nganh.toUpperCase(Locale.ROOT)) {
                    case "CNTT":
                        khoaCode = "41";
                        break;
                    case "KTPM":
                        khoaCode = "42";
                        break;
                    case "HTTT":
                        khoaCode = "43";
                        break;
                    default:
                        khoaCode = "00";
                        break;
                }
                break;
            default:
                khoaCode = "00";
                break;
        }

        idTV= "11"+ year + khoaCode + String.format("%04d", count + 1);
        int maTV= Integer.parseInt(idTV);



        ThanhVienEntity thanhVienEntity = new ThanhVienEntity(maTV, tenTV, khoa, nganh, sdt, email, password);

        // Lưu thông tin thành viên vào cơ sở dữ liệu
        tvRepository.save(thanhVienEntity);
    }

}