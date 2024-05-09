package com.example.QLThanhVien.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class XuLyViPhamController {

    @RequestMapping("/XuLyViPham.html")
    public String setData(){
        return "XuLyViPham.html";
    }
}
