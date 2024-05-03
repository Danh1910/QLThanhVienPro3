package com.example.QLThanhVien.Controller;


import com.example.QLThanhVien.Enity.ThietBiEntity;
import com.example.QLThanhVien.Repository.ThietBiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThietBiController {

    @Autowired
    private ThietBiRepository thietBiRepository;

    @GetMapping("/all")
    public String getAll(Model m)
    {
        Iterable<ThietBiEntity> list = thietBiRepository.findAll();
        m.addAttribute("data", list);
        return "ThietBiView";

    }





    @RequestMapping("/ThietBiView.html")
    public String sayHello(Model model){
        System.out.println("Say hello1");
        model.addAttribute("message", "Quản lý thiết bị");
        return "ThietBiView.html";
    }
}
