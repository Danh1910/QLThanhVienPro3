package com.example.QLThanhVien.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        ThanhVienEnity thanhVien = tvRepository.findById(Long.valueOf(maTV)).orElse(null);

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
    public String updateThongTinTV(@RequestParam Long maTV,
                                   @RequestParam String ten,
                                   @RequestParam String khoa,
                                   @RequestParam String nganh,
                                   @RequestParam String password,
                                   @RequestParam String email,
                                   @RequestParam String sdt) {
        // Tìm thông tin của thành viên dựa trên mã thành viên
        ThanhVienEnity thanhVien = tvRepository.findById(maTV).orElse(null);

        // Kiểm tra xem thành viên có tồn tại không
        if (thanhVien != null) {
            // Cập nhật thông tin thành viên
            thanhVien.setTen(ten);
            thanhVien.setKhoa(khoa);
            thanhVien.setNganh(nganh);
            thanhVien.setPassword(password);
            thanhVien.setEmail(email);
            thanhVien.setSDT(sdt);
            tvRepository.save(thanhVien); // Lưu thông tin cập nhật vào cơ sở dữ liệu
            return "redirect:/ThongTinTV.html?maTV=" + maTV; // Chuyển hướng đến trang thông tin thành viên sau khi cập nhật thành công
        } else {
            // Nếu không tìm thấy thành viên, có thể xử lý theo ý của bạn, ví dụ chuyển hướng đến trang lỗi
            return "redirect:/error.html";
        }
    }



}
