package com.example.QLThanhVien.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThietBiController {

    @RequestMapping("/tables.html")
    public String sayHello(){
        System.out.println("Say hello1");
        return "tables.html";
    }
}
