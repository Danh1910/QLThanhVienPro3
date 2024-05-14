package com.example.QLThanhVien.Controller;

import com.example.QLThanhVien.Enity.ThanhVienEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

import com.example.QLThanhVien.Repository.ThanhVienRepository;
import com.example.QLThanhVien.Repository.ThongTinSuDungRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ThongTinTVController {
    @Autowired
    private ThanhVienRepository tvRepository;
    @Autowired
    private ThongTinSuDungRepository ttsdRepository;


    @GetMapping("/ThongTinTV.html")
    public String getThongTinTV(@RequestParam Integer maTV, Model model) {
        // Tìm thông tin của thành viên dựa trên mã thành viên
        ThanhVienEntity thanhVien = tvRepository.findById(Long.valueOf(maTV)).orElse(null);

        // Kiểm tra xem thành viên có tồn tại không
        if (thanhVien != null) {
            model.addAttribute("thanhVien", thanhVien); // Truyền thông tin thành viên vào model
            model.addAttribute("ten", thanhVien.getTen());
            model.addAttribute("email", thanhVien.getEmail());
            model.addAttribute("khoa", thanhVien.getKhoa());
            model.addAttribute("nganh", thanhVien.getNganh());
            model.addAttribute("password", thanhVien.getPassword());
            model.addAttribute("sdt", thanhVien.getSDT());
            return "ThongTinTV"; // Trả về trang thông tin thành viên
        } else {
            // Nếu không tìm thấy thành viên, có thể xử lý theo ý của bạn, ví dụ chuyển hướng đến trang lỗi
            return "redirect:/error.html";
        }
    }

    @PutMapping("/ThongTinTV.html")
    public ResponseEntity<String> updateThongTinTV(@RequestParam Long maTV,
                                                   @RequestParam String ten,
                                                   @RequestParam String khoa,
                                                   @RequestParam String nganh,
                                                   @RequestParam String password,
                                                   @RequestParam String email,
                                                   @RequestParam String sdt) {
        // Tìm thông tin của thành viên dựa trên mã thành viên
        ThanhVienEntity thanhVien = tvRepository.findById(maTV).orElse(null);

        // Kiểm tra xem thành viên có tồn tại không
        if (thanhVien != null) {
            // Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu hay không
            boolean isEmailExists = checkEmailExists(email);
            boolean isSDTExists = checkSDTExists(sdt);
            
            // Nếu email đã tồn tại và không phải của thành viên hiện tại, trả về mã lỗi 400 - Bad Request
            if (isEmailExists && !thanhVien.getEmail().equals(email)) {
                return ResponseEntity.badRequest().body("Email đã tồn tại trong hệ thống.");
            } else if (isSDTExists && !thanhVien.getSDT().equals(sdt)) {
                return ResponseEntity.badRequest().body("SDT đã tồn tại trong hệ thống.");
            }

            // Cập nhật thông tin thành viên
            thanhVien.setTen(ten);
            thanhVien.setKhoa(khoa);
            thanhVien.setNganh(nganh);
            thanhVien.setPassword(password);
            thanhVien.setEmail(email);
            thanhVien.setSDT(sdt);
            tvRepository.save(thanhVien); // Lưu thông tin cập nhật vào cơ sở dữ liệu

            return ResponseEntity.ok().body("Thông tin thành viên đã được cập nhật thành công.");
        } else {
            // Nếu không tìm thấy thành viên, trả về mã lỗi 404 - Not Found
            return ResponseEntity.notFound().build();
        }
    }


    // Phương thức kiểm tra xem email đã tồn tại trong cơ sở dữ liệu hay không
    private boolean checkEmailExists(String email) {
        // Lấy danh sách tất cả các thành viên từ cơ sở dữ liệu
        Iterable<ThanhVienEntity> allThanhVien = tvRepository.findAll();

        // Duyệt qua từng thành viên để kiểm tra email
        for (ThanhVienEntity thanhVien : allThanhVien) {
            // Nếu email của thành viên hiện tại trùng với email được nhập từ form
            if (thanhVien.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkSDTExists(String sdt) {
        // Lấy danh sách tất cả các thành viên từ cơ sở dữ liệu
        Iterable<ThanhVienEntity> allThanhVien = tvRepository.findAll();

        // Duyệt qua từng thành viên để kiểm tra email
        for (ThanhVienEntity thanhVien : allThanhVien) {
            // Nếu email của thành viên hiện tại trùng với email được nhập từ form
            if (thanhVien.getSDT().equals(sdt)) {
                return true;
            }
        }
        return false;
    }




}
