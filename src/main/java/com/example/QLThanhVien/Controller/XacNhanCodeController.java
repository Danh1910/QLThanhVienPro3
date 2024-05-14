package com.example.QLThanhVien.Controller;

import com.example.QLThanhVien.Repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class XacNhanCodeController {
    @Autowired
    private ThanhVienRepository tvRepository;

    @Autowired
    private JavaMailSender emailSender;

    @RequestMapping("/XacNhanCode.html")
    public String quenmatkhau(){
        return "/XacNhanCode.html";
    }
}
