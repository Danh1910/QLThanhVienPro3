package com.example.QLThanhVien.Controller;


import com.example.QLThanhVien.Enity.ThanhVienEntity;
import com.example.QLThanhVien.Repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Locale;


@Controller
public class LoginController {
    @Autowired
    private ThanhVienRepository tvRepository;

    @RequestMapping("/DangNhap.html")
    public String loginxin(){
        return "/DangNhap.html";
    }

    @PostMapping("/getMatvFromEmail")
    public ResponseEntity<Integer> getMatvFromEmail(@RequestParam("email") String email) {
        int maTV = getMaTVByEmail(email);

        if (maTV != -1) {
            return ResponseEntity.ok(maTV);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-1);
        }
    }

    @PostMapping("/DangNhap.html")
    public ResponseEntity<String> login(@RequestParam String maTV, @RequestParam String password, Model model) {
        ThanhVienEntity user = null;

        if (!maTV.isEmpty()) {
            user = tvRepository.findById(Long.valueOf(maTV)).orElse(null);
        }

        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("thanhVien", user);
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mã thành viên hoặc mật khẩu không đúng");
        }
    }

    private int getMaTVByEmail(String email) {
        Iterable<ThanhVienEntity> allThanhVien = tvRepository.findAll();
        for (ThanhVienEntity thanhVien : allThanhVien) {
            if (thanhVien.getEmail().equals(email)) {
                return thanhVien.getMaTV();
            }
        }
        return -1;
    }

    @PostMapping("/DangKy.html")
    public ResponseEntity<String> addMember(@RequestParam(name = "Ten") String tenTV,
                                            @RequestParam(name = "Khoa") String khoa,
                                            @RequestParam(name = "Nganh") String nganh,
                                            @RequestParam(name = "SDT") String sdt,
                                            @RequestParam(name = "Email") String email,
                                            @RequestParam(name = "Password") String password) {

        Iterable<ThanhVienEntity> listtv= tvRepository.findAll();

        for (ThanhVienEntity tv : listtv) {
            if (tv.getSDT().equals(sdt)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Số điện thoại đã tồn tại");
            }
            if (tv.getEmail().equals(email)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email đã tồn tại");
            }
        }
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

        ThanhVienEntity thanhVienEntity = new ThanhVienEntity(maTV, tenTV, khoa, nganh, sdt, password, email);
        tvRepository.save(thanhVienEntity);
        return ResponseEntity.ok("Thành viên đã được thêm thành công");
    }
}
