package com.example.QLThanhVien.Repository;


import com.example.QLThanhVien.Enity.XuLyViPhamEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XuLyViPhamRepository extends CrudRepository<XuLyViPhamEntity, Integer> {

    @Query("SELECT xl FROM XuLyViPhamEntity xl WHERE xl.MaTV.MaTV = :maTV AND xl.trang_thaixl = 0")
    List<XuLyViPhamEntity> findByMaTV(Integer maTV);

    @Query("SELECT DISTINCT x.hinh_thucxl FROM XuLyViPhamEntity x")
    List<String> getDistinctHinhThucXl();

}
