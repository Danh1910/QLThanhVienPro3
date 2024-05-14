package com.example.QLThanhVien.Controller;

import com.example.QLThanhVien.Enity.ThanhVienEntity;
import com.example.QLThanhVien.Repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.*;

@Controller
public class QuenMatKhauController {
    @Autowired
    private ThanhVienRepository tvRepository;

    @Autowired
    private JavaMailSender emailSender;

    private Map<String, String> emailCodeMap = new HashMap<>();
    private Map<String, String> codeMaTVMap = new HashMap<>(); // Lưu mã thành viên với mã xác nhận

    @RequestMapping("/QuenMatKhau.html")
    public String quenmatkhau() {
        return "/QuenMatKhau.html";
    }

    @PostMapping("/QuenMatKhau.html")
    public ResponseEntity<String> sendResetPasswordEmail(@RequestParam(name = "Email") String email, Model model) {
        Iterable<ThanhVienEntity> allThanhVien = tvRepository.findAll();

        String code = generateRandomCode();
        boolean emailFound = false;

        for (ThanhVienEntity thanhVien : allThanhVien) {
            if (thanhVien.getEmail().equals(email)) {
                emailFound = true;
                sendEmail(email, code);
                emailCodeMap.put(email, code);
                codeMaTVMap.put(code, String.valueOf(thanhVien.getMaTV())); // Chuyển đổi maTV thành String
                model.addAttribute("message", "Reset password email sent successfully!");
                return ResponseEntity.ok().body("Thông tin thành viên đã được cập nhật thành công.");
            }
        }

        if (!emailFound) {
            model.addAttribute("error", "Email not found in the database.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email không tồn tại trong cơ sở dữ liệu.");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
    }

    @PostMapping("/VerifyCode")
    public ResponseEntity<Map<String, String>> verifyCode(@RequestBody Map<String, String> request) {
        String code = request.get("code");
        if (emailCodeMap.containsValue(code)) {
            String maTV = codeMaTVMap.get(code); // Lấy mã thành viên từ mã xác nhận
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("maTV", maTV);
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("status", "fail");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    private String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    private void sendEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Code");
        message.setText("Your password reset code is: " + code);
        emailSender.send(message);
    }
}
