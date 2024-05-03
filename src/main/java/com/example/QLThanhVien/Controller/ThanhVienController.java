/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.QLThanhVien.Controller;


import com.example.QLThanhVien.Enity.ThanhVienEnity;
import com.example.QLThanhVien.Repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ASUS
 */
@Controller
public class ThanhVienController {

    @Autowired
    private ThanhVienRepository tvRepository;

//    @GetMapping("/ThanhVien") // Đổi URL thành /thanh-vien/all
//    public String getAll(Model m)
//    {
//        System.out.println("get aLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
//        Iterable<ThanhVienEnity> list= tvRepository.findAll();
//        m.addAttribute("data",list);
//        return "ThanhVien";
//    }
    @RequestMapping("/ThanhVien.html")
    public String sayHello(Model model){
        System.out.println("Say hello1");
        model.addAttribute("message", "Quản lý thành viên");
        Iterable<ThanhVienEnity> list= tvRepository.findAll();
        model.addAttribute("data",list);

        return "ThanhVien.html";
    }
}
