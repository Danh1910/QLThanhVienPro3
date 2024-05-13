package com.example.QLThanhVien.Controller;

import com.example.QLThanhVien.Enity.ThanhVienEntity;
import com.example.QLThanhVien.Enity.ThietBiEntity;
import com.example.QLThanhVien.Enity.ThongTinSuDungEntity;
import com.example.QLThanhVien.Enity.XuLyViPhamEntity;
import com.example.QLThanhVien.Repository.ThietBiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.QLThanhVien.Repository.ThanhVienRepository;
import com.example.QLThanhVien.Repository.ThongTinSuDungRepository;
import com.example.QLThanhVien.Repository.XuLyViPhamRepository;

import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DatMuonController {
    @Autowired
    private ThanhVienRepository tvRepository;

    @Autowired
    private ThietBiRepository thietBiRepository;

    @Autowired
    private ThongTinSuDungRepository ttsdRepository;

    @Autowired
    private XuLyViPhamRepository xLyViPhamRepository;


    @RequestMapping("/MuonTB.html")
    public String LoadData(Model model){

        model.addAttribute("message", "Đặt mượn thiết bị");

        Iterable<ThietBiEntity> list= thietBiRepository.findAll();


        model.addAttribute("data",list);

        return "MuonTB.html";
    }

    @PostMapping("/MuonTB.html")
    public ResponseEntity<Map<String, String>> addThongTin(@RequestParam(name = "MaTV") ThanhVienEntity MaTV, @RequestParam(name = "MaTB") ThietBiEntity MaTB, @RequestParam(name = "TGianDatCho") String TGianDatCho){
        Date ngayDatdate = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            ngayDatdate = formatter.parse(TGianDatCho);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (CheckViPham(MaTV)){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Bạn đang vi phạm nên không thể đặt chỗ thiết bị");
            return ResponseEntity.ok().body(response);
        }
        
        if (CheckDatCho(MaTB, ngayDatdate)) {

            ThongTinSuDungEntity thongTinSuDungEntity = new ThongTinSuDungEntity(MaTV,MaTB,ngayDatdate);
            ttsdRepository.save(thongTinSuDungEntity);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Đặt chỗ thiết bị thành công");
            return ResponseEntity.ok().body(response);
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Không thể đặt chỗ vì thiết bị đã được đặt chỗ hoặc đang mượn");
        return ResponseEntity.ok().body(response);
    }

    public boolean CheckDatCho(ThietBiEntity MaTB, Date TGianDatCho){
        
        Iterable<ThongTinSuDungEntity> list = ttsdRepository.findAll();
    
        for (ThongTinSuDungEntity temp: list){

            if (temp.getMaTB() == MaTB && temp.getTGTra() != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                
                String strDate3 = formatter.format(temp.getTGTra());
                String strDate4 = formatter.format(TGianDatCho);

                if (strDate3.equals(strDate4)) {
                    System.out.println("Không thể đặt thêm chỗ vì thiết bị đang được mượn.");
                    return false;
                    
                }
                
            }
            else if (temp.getMaTB() == MaTB && temp.getTGDatCho() != null) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = formatter.format(TGianDatCho);
                String strDate2 = formatter.format(temp.getTGDatCho());

                System.out.print(temp.getMaTB().getTenTB() + "  \n");
                System.out.print(strDate2+"\n");
                System.out.print(strDate + "\n");
                
                if (strDate2.equals(strDate)) {
                    
                    System.out.println("Không thể đặt thêm chỗ vì thiết bị đã được đặt chỗ trong khoảng thời gian này.");
                    return false;
                }
            }
        }
    
        // Nếu không có thông tin sử dụng nào trùng với MaTB và TGianDatCho thì có thể đặt chỗ, trả về true
        return true;
    }


    public boolean CheckViPham(ThanhVienEntity MaTV){

        Iterable<XuLyViPhamEntity> listvp = xLyViPhamRepository.findAll();
        System.out.println("Hàm đã chạy \n");
        for (XuLyViPhamEntity temp : listvp){
            System.out.println("vòng for \n");
            if (temp.getMaTV() == MaTV ) {
                System.out.println("MSSV: " + MaTV.getMaTV());
                if (temp.getTrang_thaixl() == 0) {
                    System.out.println("Thanh niên này đang vi phạm");
                    return true;
                }
            }
        }


        return true;
    }
}
