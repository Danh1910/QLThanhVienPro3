package com.example.QLThanhVien.Controller;

import com.example.QLThanhVien.Enity.ThanhVienEntity;
import com.example.QLThanhVien.Enity.XuLyViPhamEntity;
import com.example.QLThanhVien.Repository.XuLyViPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class XuLyViPhamController {
    @Autowired
    private XuLyViPhamRepository xlvpRepository;
    @RequestMapping("/XuLyViPham.html")
    public String LoadData(Model model){


        Iterable<XuLyViPhamEntity> list= xlvpRepository.findAll();

        model.addAttribute("data",list);


        return "XuLyViPham.html";
    }

    @PutMapping("/XuLyViPham.html")
    public void editXLVP(@RequestParam(name = "MaXL") Integer MaXL, @RequestParam(name = "MaTV") ThanhVienEntity MaTV, @RequestParam(name = "HTXL") String HTXL, @RequestParam(name = "SoTien") Integer SoTien, @RequestParam(name = "NgayXL") String NgayXL, @RequestParam(name = "TrangThaiXL") Integer TrangThaiXL) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = format.parse(NgayXL);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        XuLyViPhamEntity xlvpEntity = new XuLyViPhamEntity(MaXL,MaTV,HTXL,SoTien,date,TrangThaiXL);
        xlvpRepository.save(xlvpEntity);

    }

    @PostMapping("/XuLyViPham.html")
    public void addXLVP(@RequestParam(name = "MaTV") ThanhVienEntity MaTV, @RequestParam(name = "HTXL") String HTXL, @RequestParam(name = "SoTien") Integer SoTien, @RequestParam(name = "NgayXL") String NgayXL, @RequestParam(name = "TrangThaiXL") Integer TrangThaiXL){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = format.parse(NgayXL);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        XuLyViPhamEntity xlvpEntity = new XuLyViPhamEntity(MaTV,HTXL,SoTien,date,TrangThaiXL);
        xlvpRepository.save(xlvpEntity);
    }

    @DeleteMapping("/XuLyViPham.html")
    public void deleteXLVP(@RequestBody List<Integer> list_id){

        for (Integer id : list_id){


            Iterable<XuLyViPhamEntity> list = xlvpRepository.findAll();

            // Danh sách xlvp
            Optional<XuLyViPhamEntity> temp = xlvpRepository.findById(id);

            if (temp.isPresent()) {

                // Ep kieu thanh kieu xlvpEntity
                XuLyViPhamEntity xlvp = temp.get();

                // Kiem tra xem vi pham da duoc xu ly hay chua

                if (xlvp.getTrang_thaixl() == 0){

                }
                else{
                    XoaHetXLVP((List<XuLyViPhamEntity>) list,id);
                    xlvpRepository.delete(xlvp);
                }

            }
        }

    }
    public void XoaHetXLVP(List<XuLyViPhamEntity> list,int MaXL){
        for (XuLyViPhamEntity temp : list){
            if (temp.getMaXL() != null) {
                if (temp.getMaXL() == MaXL) {
                    xlvpRepository.delete(temp);
                }
            }
        }
    }

}
