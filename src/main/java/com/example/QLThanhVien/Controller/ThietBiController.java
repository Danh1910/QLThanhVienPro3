package com.example.QLThanhVien.Controller;


import com.example.QLThanhVien.Enity.ThietBiEntity;
import com.example.QLThanhVien.Repository.ThietBiRepository;
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


            Optional<ThietBiEntity> temp = thietBiRepository.findById(id);

            if (temp.isPresent()) {
                ThietBiEntity thietBi = temp.get();

                thietBiRepository.delete(thietBi);
            } else {
                System.out.println("Khong tim ra " + id);
            }

        }

    }


}
