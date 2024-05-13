package com.example.QLThanhVien.Controller;


import com.example.QLThanhVien.Enity.ThietBiEntity;
import com.example.QLThanhVien.Enity.ThongTinSuDungEntity;
import com.example.QLThanhVien.Repository.ThanhVienRepository;
import com.example.QLThanhVien.Repository.ThietBiRepository;
import com.example.QLThanhVien.Repository.ThongTinSuDungRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

import java.util.*;


@Controller
public class ThietBiController {

    @Autowired
    private ThietBiRepository thietBiRepository;

    @Autowired
    private ThongTinSuDungRepository thongTinSuDungRepository;

    @Autowired
    private ThanhVienRepository tvRepository;






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

    @PatchMapping("/ThietBi.html")
    public ResponseEntity<String> addExcel(@RequestParam("file") MultipartFile file){
        ArrayList<ThietBiEntity> list_excel = new ArrayList<>();
        try{
            byte[] fileBytes = file.getBytes();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

            Row Titlerow = sheet.getRow(0);

            Cell TitleMaCell = Titlerow.getCell(0);
            Cell TitleTenCell = Titlerow.getCell(1);
            Cell TitleMoTaCell = Titlerow.getCell(2);

            if (TitleMaCell != null && TitleTenCell != null && TitleMoTaCell != null){
                String ma = TitleMaCell.getStringCellValue();
                String ten = TitleTenCell.getStringCellValue();
                String mota = TitleMoTaCell.getStringCellValue();
                if (!ma.equals("MaTB") || !ten.equals("TenTB") || !mota.equals("MoTaTB")){
                    return ResponseEntity.ok("File không đúng cấu trúc cột"); // Ví dụ: trả về một thông báo thành công
                }


                // Bắt đầu đọc từ hàng thứ hai (index 1)
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        Cell MaCell = row.getCell(0);
                        Cell TenCell = row.getCell(1);
                        Cell MoTaCell = row.getCell(2);

                        // Kiểm tra và lưu dữ liệu vào Hibernate
                        if (MaCell  != null && TenCell != null && MoTaCell != null) {

                            double Ma = 0;
                            String Ten = "";
                            String MoTa = "";

                            try{
                                Ma = MaCell.getNumericCellValue();
                                Ten = TenCell.getStringCellValue();
                                MoTa = MoTaCell.getStringCellValue();
                            }
                            catch(Exception e){
                                return ResponseEntity.ok("MaTB là số, TenTB và MoTaTB là chuỗi ký tự"); // Ví dụ: trả về một thông báo thành công
                            }

                            ThietBiEntity thietbi = new ThietBiEntity((int) Ma, Ten, MoTa);

                            list_excel.add(thietbi);

                        }
                    }
                }
            }

            for (ThietBiEntity a : list_excel){
                thietBiRepository.save(a);
            }


        }catch(Exception e){
            e.printStackTrace();
        }


        return ResponseEntity.ok("Thêm thành công"); // Ví dụ: trả về một thông báo thành công
    }




    @DeleteMapping("/ThietBi.html")
    public ResponseEntity<String> deleteDevice(@RequestBody List<Integer> list_id){

        int dem = 0;


        for (Integer id : list_id){

            // Danh sach thong tin su dung
            List<ThongTinSuDungEntity> list = thongTinSuDungRepository.LayThongTinSuDungTB();

            // Lay thiet bi
            Optional<ThietBiEntity> temp = thietBiRepository.findById(id);


            if (temp.isPresent()) {

                // Ep kieu thanh kieu thietbiEntity
                ThietBiEntity thietBi = temp.get();

                // Kiem tra xem thiet bi co dand duoc muon hay dat cho khong
                ThongTinSuDungEntity thongTinSuDung = CheckMuonvaDatCho((List<ThongTinSuDungEntity>) list,thietBi.getMaTB());


                if (thongTinSuDung != null){
                    System.out.println(thongTinSuDung.getMaTB().getMaTB());
                    dem++;
                }
                else{
                    XoaHetThongTinSuDung((List<ThongTinSuDungEntity>) list,id);
                    thietBiRepository.delete(thietBi);

                }


            }
        }

        if (dem != 0){
            return ResponseEntity.ok("Hệ thống sẽ không xóa 1 số thiết bị đang được đặt chỗ hoặc đang được mượn");
        }

        return ResponseEntity.ok("Đã xóa thiết bị thành công");

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

    @PostMapping("/ThietBi.html")
    public void addDevice(@RequestParam(name = "LoaiTBIndex") String loaiTB, @RequestParam(name = "TenTB") String tenTB, @RequestParam(name = "MoTaTB") String motaTB){
        int SoLoaiTB= Integer.parseInt(loaiTB)+1;
        String IdTB;
        Calendar cal = Calendar.getInstance();
        // Lấy năm hiện tại
        int year = cal.get(Calendar.YEAR);
        int stt= layIdLoaiTB(SoLoaiTB);
        IdTB = Integer.toString(SoLoaiTB)+Integer.toString(year)+Integer.toString(stt);
        ThietBiEntity thietBiEntity = new ThietBiEntity(Integer.parseInt(IdTB),tenTB,motaTB);
        thietBiRepository.save(thietBiEntity);
    }
    public int layIdLoaiTB(int loaiTB_selected){
        Iterable<ThietBiEntity> list= thietBiRepository.findAll();
        int dem=0;
        for(ThietBiEntity x : list){
            if(KtLoaiTB(x,loaiTB_selected)){
                dem+=1;
            }
        }
        return dem+1;
    }
    public boolean KtLoaiTB(ThietBiEntity tb, int loaiTB) {
        String temp = Integer.toString(tb.getMaTB());
        char firstChar = temp.charAt(0);
        int intValue = Character.getNumericValue(firstChar);
        if (intValue == loaiTB) {
            return true;
        } else {
            return false;
        }
    }


}
