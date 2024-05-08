package com.example.QLThanhVien.Controller;


import com.example.QLThanhVien.Enity.ThietBiEntity;
import com.example.QLThanhVien.Enity.ThongTinSuDungEntity;
import com.example.QLThanhVien.Repository.ThietBiRepository;
import com.example.QLThanhVien.Repository.ThongTinSuDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class ThietBiController {

    @Autowired
    private ThietBiRepository thietBiRepository;

    @Autowired
    private ThongTinSuDungRepository thongTinSuDungRepository;






    @RequestMapping("/ThietBi.html")
    public String LoadData(Model model){

        model.addAttribute("message", "Quản lý thiết bị");

        Iterable<ThietBiEntity> list= thietBiRepository.findAll();

        model.addAttribute("data",list);


        return "ThietBi.html";
    }


    @PutMapping("/ThietBi.html")
    public void editDevice(@RequestParam(name = "MaTB") Integer maTB, @RequestParam(name = "TenTB") String tenTB, @RequestParam(name = "MoTaTB") String moTaTB, Model model) {

            ThietBiEntity thietBiEntity = new ThietBiEntity(maTB,tenTB,moTaTB);

            thietBiRepository.save(thietBiEntity);

    }

    @DeleteMapping("/ThietBi.html")
    public void deleteDevice(@RequestBody List<Integer> list_id){

        for (Integer id : list_id){

            // Danh sach thong tin su dung
            Iterable<ThongTinSuDungEntity> list = thongTinSuDungRepository.findAll();

            // Danh sách thiet bi
            Optional<ThietBiEntity> temp = thietBiRepository.findById(id);

            if (temp.isPresent()) {

                // Ep kieu thanh kieu thietbiEntity
                ThietBiEntity thietBi = temp.get();

                // Kiem tra xem thiet bi co dand duoc muon hay dat cho khong
                ThongTinSuDungEntity thongTinSuDung = CheckMuonvaDatCho((List<ThongTinSuDungEntity>) list,thietBi.getMaTB());


                if (thongTinSuDung != null){
                    System.out.println(thongTinSuDung.getMaTB().getMaTB());
                }
                else{
                    XoaHetThongTinSuDung((List<ThongTinSuDungEntity>) list,id);
                    thietBiRepository.delete(thietBi);
                }


            }
        }

    }

    public ThongTinSuDungEntity CheckMuonvaDatCho (List<ThongTinSuDungEntity> list,int idThietBi){
        for (ThongTinSuDungEntity temp : list){
            if (temp.getMaTB() != null) {
                if (temp.getMaTB().getMaTB() == idThietBi) {
                    if (temp.getTGTra() != null || temp.getTGDatCho() != null) {
                        return temp;
                    }
                }
            }
        }
        return null;
    }

    public void XoaHetThongTinSuDung(List<ThongTinSuDungEntity> list,int idThietBi){
        for (ThongTinSuDungEntity temp : list){
            if (temp.getMaTB() != null) {
                if (temp.getMaTB().getMaTB() == idThietBi) {
                    thongTinSuDungRepository.delete(temp);
                }
            }
        }
    }


}
