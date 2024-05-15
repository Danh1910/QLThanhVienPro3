package com.example.QLThanhVien.Controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.QLThanhVien.Enity.ThongTinSuDungEntity;
import com.example.QLThanhVien.Enity.XuLyViPhamEntity;
import com.example.QLThanhVien.Repository.ThongTinSuDungRepository;
import com.example.QLThanhVien.Repository.XuLyViPhamRepository;

@Controller
public class ThongKeController {
	@Autowired
    private ThongTinSuDungRepository ttsdRepository;
	
	@Autowired
    private XuLyViPhamRepository xlReponsitory;
	
	@PutMapping("/ThongKe")
	public ResponseEntity<Object[][]> action(@RequestParam("chon") String chon, @RequestParam(name = "search", required = false) String search) {

		List<ThongTinSuDungEntity> listTTSD = new ArrayList<>();
		System.out.println(search);
		System.out.println(chon);

	    // Kiểm tra nếu mã thành viên được cung cấp
	    if (search != null) {
	    	if (chon.equals("khoa")) {
	    		System.out.println("chon  khoa");
	    		listTTSD = ttsdRepository.findByMaTVKhoaContaining(search);
	    	}
	    	else {
	    		System.out.println("chon  nganh");
	    		listTTSD = ttsdRepository.findByMaTVNganhContaining(search);
	    	}
	        System.out.println(listTTSD.get(0).getMaTV().getMaTV());
	    } else {
	        listTTSD = (List<ThongTinSuDungEntity>) ttsdRepository.findAll();
	    }
	    Object[][] modObjects = new Object[listTTSD.size()][9];

	    int index = 0;
	    for (ThongTinSuDungEntity t : listTTSD) {
	        // Ánh xạ các trường của ThongTinSuDungEntity vào mảng Object
	        modObjects[index][0] = t.getMaTT();
	        modObjects[index][1] = t.getMaTV().getMaTV();
	        modObjects[index][2] = t.getMaTV().getKhoa();
	        modObjects[index][3] = t.getMaTV().getNganh();
	        modObjects[index][4] = t.getMaTB() != null? t.getMaTB().getMaTB() : ""; 
	        modObjects[index][5] = t.getTGVao() != null? t.getTGVao().toString() : "";
	        modObjects[index][6] = t.getTGMuon() != null? t.getTGMuon().toString() : "";
	        modObjects[index][7] = t.getTGTra() != null? t.getTGTra().toString() : "";
	        modObjects[index][8] = t.getTGDatCho() != null? t.getTGDatCho().toString() : "";
	        index++;
	    }
	    return new ResponseEntity<>(modObjects, HttpStatus.OK);
	}
	@PutMapping("/ThongKeXuly")
	public ResponseEntity<Object[][]> getXulWithSearch(@RequestParam("chon") String chon, @RequestParam(name = "search", required = false) String search) {
		List<XuLyViPhamEntity> listTTSD = new ArrayList<>();
	    // Kiểm tra nếu mã thành viên được cung cấp
	    if (search != null) {
	    	if (chon.equals("khoa")) {
	    		System.out.println("chon  khoa");
	    		listTTSD = xlReponsitory.findByMaTVKhoaContaining(search);
	    	}
	    	else {
	    		System.out.println("chon  nganh");
	    		listTTSD = xlReponsitory.findByMaTVNganhContaining(search);
	    	}
	        System.out.println(listTTSD.get(0).getMaTV().getMaTV());
	    } else {
	        listTTSD = (List<XuLyViPhamEntity>) xlReponsitory.findAll();
	    }

	    return new ResponseEntity<>(returnListData(listTTSD), HttpStatus.OK);
	}
	@RequestMapping("/ThongKe.html")
    public String action(Model model){
		new MuonThietBiController().XoaDatCho_1gio(ttsdRepository);

		model.addAttribute("message", "Thống kê");
        Iterable<ThongTinSuDungEntity> listTTSD = ttsdRepository.findAll();
        model.addAttribute("listTTSD",listTTSD);
        Iterable<XuLyViPhamEntity> listXLVP = xlReponsitory.findAll();
        model.addAttribute("listXLVP",listXLVP);
        List<String> hinhThucXl = xlReponsitory.getDistinctHinhThucXl();
        model.addAttribute("hinhThucXl", hinhThucXl);
        List<String> listKhoa = ttsdRepository.listKhoainTTSD();
        model.addAttribute("listKhoa",listKhoa);
        List<String> listNganh = ttsdRepository.listNganhinTTSD();
        model.addAttribute("listNganh",listNganh);
        
        return "ThongKe.html";
    }
	@GetMapping("/loadXulyData")
	@ResponseBody
    public Object[][] loadXulyData() {
        // Gọi service hoặc repository để lấy dữ liệu xử lý
		List<XuLyViPhamEntity> returnListData = (List<XuLyViPhamEntity>) xlReponsitory.findAll();
		
        return returnListData(returnListData);
    }
	@GetMapping("/loadDaXulyData")
	@ResponseBody
    public Object[][] loadDaXulyData() {
        // Gọi service hoặc repository để lấy dữ liệu xử lý
		List<XuLyViPhamEntity> xulyDataList = xlReponsitory.getXulyVipham_DaNhan();

        return returnListData(xulyDataList);
    }
	@GetMapping("/loadChuaXulyData")
	@ResponseBody
    public Object[][] loadChuaXulyData() {
        // Gọi service hoặc repository để lấy dữ liệu xử lý
		List<XuLyViPhamEntity> xulyDataList = xlReponsitory.getXulyVipham_ChuaNhan();

        return returnListData(xulyDataList);
    }
	private Object[][] returnListData(List<XuLyViPhamEntity> xulyDataList){
		Object[][] modObjects = new Object[xulyDataList.size()][8];

	    int index = 0;
	    for (XuLyViPhamEntity t : xulyDataList) {
	        modObjects[index][0] = t.getMaXL();
	        modObjects[index][1] = t.getMaTV().getMaTV();
	        modObjects[index][2] = t.getMaTV().getKhoa();
	        modObjects[index][3] = t.getMaTV().getNganh();
	        modObjects[index][4] = t.getHinh_thucxl(); 
	        modObjects[index][5] = t.getSo_tien();
	        modObjects[index][6] = t.getNgayXL().toString();
	        modObjects[index][7] = t.getTrang_thaixl() == 0 ? "Chưa xử lý" : "Đã xử lý";
	        index++;
	    }
        return modObjects;
	}
	
}


