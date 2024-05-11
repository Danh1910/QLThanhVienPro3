package com.example.QLThanhVien.Controller;

import com.example.QLThanhVien.Enity.ThanhVienEntity;
import com.example.QLThanhVien.Enity.ThietBiEntity;
import com.example.QLThanhVien.Enity.ThongTinSuDungEntity;
import com.example.QLThanhVien.Repository.ThanhVienRepository;
import com.example.QLThanhVien.Repository.ThietBiRepository;
import com.example.QLThanhVien.Repository.ThongTinSuDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MuonThietBiController {

    @Autowired
    private ThietBiRepository thietBiRepository;

    @Autowired
    private ThongTinSuDungRepository thongTinSuDungRepository;

    @Autowired
    private ThanhVienRepository tvRepository;

    private int MaTV = 0;



    @RequestMapping("/MuonThietBi/{maTV}")
    public String openFormMuon(@PathVariable("maTV") int maTV, Model model) {

        MaTV = maTV;

        ThanhVienEntity tv=   tvRepository.findById(Long.valueOf(maTV)).orElse(null);

        if(tv !=null){
            model.addAttribute("thanhVien",tv);
        }
        return "MuonThietBi"; // Trả về tên của trang HTML
    }


    @PostMapping("/MuonThietBi.html")
    public ResponseEntity<String> addMuon(@RequestParam(name = "MaTV") Integer MaTV, @RequestParam (name = "MaTB") Integer MaTB, @RequestParam (name = "NgayMuon") String NgayMuon, @RequestParam (name = "NgayTra") String NgayTra){

        ThongTinSuDungEntity thongTinSuDung = new ThongTinSuDungEntity();

        System.out.println(NgayMuon);

        // Set up thông tin truoc khi them vao

        ThanhVienEntity TV = tvRepository.findById(Long.valueOf(MaTV)).orElse(null);

        ThietBiEntity TB = thietBiRepository.findById(MaTB).orElse(null);

        Date ngayMuonDate = new Date();

        Date ngayTraDate = new Date();

        // Định dạng đầu vào
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            ngayMuonDate = formatter.parse(NgayMuon);
            ngayTraDate = formatter.parse(NgayTra);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        if (TV == null || TB == null){
            return ResponseEntity.ok("Không tìm thấy thành viên hoặc thiết bị");
        }

        thongTinSuDung.setMaTV(TV);
        thongTinSuDung.setMaTB(TB);
        thongTinSuDung.setTGMuon(ngayMuonDate);
        thongTinSuDung.setTGTra(ngayTraDate);

        thongTinSuDungRepository.save(thongTinSuDung);


        return ResponseEntity.ok("Mượn thành công");
    }







    @PatchMapping("/MuonThietBi.html")
    public ResponseEntity<List<ThietBiEntity>> List_TB(){

        Iterable<ThietBiEntity> list_TB = thietBiRepository.findAll();

        List<ThietBiEntity> list = getThietBiMuon((List<ThietBiEntity>) list_TB,MaTV);


        return ResponseEntity.ok(list);
    }


    public List<ThietBiEntity> getThietBiMuon (List<ThietBiEntity> TB_list, int MaTV){

        List<com.example.QLThanhVien.Enity.ThietBiEntity> list = new ArrayList<>();

        List<ThongTinSuDungEntity> list_ttsd = thongTinSuDungRepository.LayThongTinSuDungTB();

        for (com.example.QLThanhVien.Enity.ThietBiEntity tb : TB_list){
            if (CheckThietBiChoMuon(list_ttsd,tb.getMaTB(),MaTV)){
                list.add(tb);
            }
        }

        return list;
    }


    public boolean CheckThietBiChoMuon (List<ThongTinSuDungEntity> list,int idThietBi, int MaTV){
        for (ThongTinSuDungEntity temp : list){
            if (temp.getMaTB() != null && temp.getMaTV() != null) {
                if (temp.getMaTB().getMaTB() == idThietBi) {
                    if (temp.getTGTra() != null || (temp.getTGDatCho() != null && temp.getMaTV().getMaTV() != MaTV)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
