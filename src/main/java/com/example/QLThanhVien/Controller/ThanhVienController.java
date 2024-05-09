/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.QLThanhVien.Controller;


import com.example.QLThanhVien.Enity.ThanhVienEnity;
import com.example.QLThanhVien.Repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

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
        model.addAttribute("message", "Quản lý thành viên");
        Iterable<ThanhVienEnity> list= tvRepository.findAll();
        model.addAttribute("data",list);

        return "ThanhVien.html";
    }


//    @RequestMapping("/edit/{MaTV}")
//    public String showEdit(@PathVariable("MaTV") int maTV, Model model){
//        ThanhVienEnity thanhVien = tvRepository.findById(Long.valueOf(maTV)).orElse(null);
//        System.out.println(thanhVien.getTen());
//        if (thanhVien != null) {
//            // Truyền thông tin thành viên vào model để hiển thị trên form
//            model.addAttribute("thanhVien", thanhVien);
//            return "editForm.html"; // Trả về view chứa form chỉnh sửa
//        }
//        return "redirect:/ThanhVien.html";
//    }
//    @PostMapping("/edit/{maTV}")
//    public String processEditForm(@PathVariable("maTV") int maTV, @ModelAttribute ThanhVienEnity thanhVien) {
//        // Lưu thông tin thành viên đã chỉnh sửa vào cơ sở dữ liệu
//        tvRepository.save(thanhVien);
//        // Chuyển hướng đến trang hiển thị danh sách thành viên sau khi chỉnh sửa thành công
//        return "redirect:/ThanhVien.html"; // Thay "/ThanhVien.html" bằng URL của trang hiển thị danh sách thành viên của bạn
//    }
}
