package com.example.QLThanhVien.Repository;


import com.example.QLThanhVien.Enity.ThongTinSuDungEntity;
import com.example.QLThanhVien.Enity.XuLyViPhamEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XuLyViPhamRepository extends CrudRepository<XuLyViPhamEntity, Integer> {

    @Query("SELECT xl FROM XuLyViPhamEntity xl WHERE xl.MaTV.MaTV = :maTV AND xl.trang_thaixl = 0")
    List<XuLyViPhamEntity> findByMaTV(Integer maTV);

    @Query("SELECT DISTINCT x.hinh_thucxl FROM XuLyViPhamEntity x")
    List<String> getDistinctHinhThucXl();
    
    @Query("SELECT xl FROM XuLyViPhamEntity xl WHERE xl.trang_thaixl = 1")
    List<XuLyViPhamEntity> getXulyVipham_DaNhan();
    
    @Query("SELECT xl FROM XuLyViPhamEntity xl WHERE xl.trang_thaixl = 0")
    List<XuLyViPhamEntity> getXulyVipham_ChuaNhan();

    @Query(value = "SELECT tsd.* "
			+ "FROM xuly tsd "
			+ "JOIN thanhvien tv ON tsd.MaTV = tv.MaTV "
			+ "WHERE tv.Khoa LIKE %:khoa%", nativeQuery = true)
	List<XuLyViPhamEntity> findByMaTVKhoaContaining(@Param("khoa") String khoa);
	
	@Query(value = "SELECT tsd.* "
			+ "FROM xuly tsd "
			+ "JOIN thanhvien tv ON tsd.MaTV = tv.MaTV "
			+ "WHERE tv.Nganh LIKE %:nganh%", nativeQuery = true)
	List<XuLyViPhamEntity> findByMaTVNganhContaining(@Param("nganh") String nganh);
}
