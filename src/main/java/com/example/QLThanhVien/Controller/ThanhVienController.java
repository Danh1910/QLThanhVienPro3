/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.QLThanhVien.Controller;


import com.example.QLThanhVien.Enity.ThanhVienEntity;
import com.example.QLThanhVien.Enity.ThietBiEntity;
import com.example.QLThanhVien.Enity.ThongTinSuDungEntity;
import com.example.QLThanhVien.Enity.XuLyViPhamEntity;
import com.example.QLThanhVien.Repository.ThanhVienRepository;
import com.example.QLThanhVien.Repository.ThietBiRepository;
import com.example.QLThanhVien.Repository.ThongTinSuDungRepository;
import com.example.QLThanhVien.Repository.XuLyViPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author ASUS
 */
@Controller
public class ThanhVienController {

    @Autowired
    private ThanhVienRepository tvRepository;

    @Autowired
    private ThietBiRepository tbRepository;

    @Autowired
    private ThongTinSuDungRepository ttsdRepository;

    @Autowired
    private XuLyViPhamRepository  xlvpRepository;

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
        Iterable<ThanhVienEntity> list= tvRepository.findAll();
        model.addAttribute("data",list);

        return "ThanhVien.html";
    }


    @PutMapping("/ThanhVien.html")
    public void editThanhVien(@RequestParam(name = "MaTV") Integer maTV, @RequestParam(name = "Ten") String ten, @RequestParam(name = "Khoa") String khoa, @RequestParam(name = "Nganh") String nganh, @RequestParam(name = "SDT") String sdt, @RequestParam(name = "Email") String email, @RequestParam(name = "Password") String password, Model model) {

        ThanhVienEntity tvEntity = new ThanhVienEntity(maTV,ten,khoa,nganh,sdt,email,password);

        tvRepository.save(tvEntity);

    }

    @DeleteMapping("/ThanhVien.html")
    public void deleteThanhVien(@RequestBody List<Integer> list_id){

        for (Integer id : list_id){

            // Danh sach thong tin su dung
            Iterable<ThongTinSuDungEntity> listTT = ttsdRepository.findAll();

            Iterable<XuLyViPhamEntity> listVP = xlvpRepository.findAll();

            // Danh sách thiet bi
            Optional<ThanhVienEntity> temp = tvRepository.findById(Long.valueOf(id));

            if (temp.isPresent()) {

                // Ep kieu thanh kieu thietbiEntity
                ThanhVienEntity tVien = temp.get();

                if(CheckMuonvaDatCho((List<ThongTinSuDungEntity>) listTT,tVien.getMaTV()) && CheckVP((List<XuLyViPhamEntity>) listVP,tVien.getMaTV())){
                    tvRepository.deleteById(Long.valueOf(tVien.getMaTV()));
                }


            }
        }

    }
    public boolean CheckMuonvaDatCho (List<ThongTinSuDungEntity> list,int idTV){
        for (ThongTinSuDungEntity temp : list){
            if (temp.getMaTV() != null) {
                if (temp.getMaTV().getMaTV() == idTV) {
                    if (temp.getTGVao() == null) {
                        return false;
                    }
                }
            }
        }
        for (ThongTinSuDungEntity temp : list){
            if (temp.getMaTV() != null) {
                if (temp.getMaTV().getMaTV() == idTV) {
                    if (temp.getTGVao() != null) {
                        List<Integer> arr= tvRepository.getIDByMaTV(idTV);
                        for(Integer a:arr){
                            ttsdRepository.deleteById(a);
                        }
                    }
                }
            }
        }

        return true;
    }

    public boolean CheckVP(List<XuLyViPhamEntity> list,int idTV){
        for(XuLyViPhamEntity arr: list){
            if(arr.getMaTV()==idTV && arr.getTrang_thaixl()==0){
                return false;
            }
        }
        return true;
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
