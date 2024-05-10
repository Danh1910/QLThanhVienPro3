package com.example.QLThanhVien.Repository;

import com.example.QLThanhVien.Enity.ThongTinSuDungEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThongTinSuDungRepository extends CrudRepository<ThongTinSuDungEntity, Integer> {
	@Query(value = "SELECT tsd.* "
			+ "FROM thongtinsd tsd "
			+ "JOIN thanhvien tv ON tsd.MaTV = tv.MaTV "
			+ "WHERE tv.Khoa LIKE %:khoa%", nativeQuery = true)
	List<ThongTinSuDungEntity> findByMaTVKhoaContaining(@Param("khoa") String khoa);
	
	@Query(value = "SELECT tsd.* "
			+ "FROM thongtinsd tsd "
			+ "JOIN thanhvien tv ON tsd.MaTV = tv.MaTV "
			+ "WHERE tv.Nganh LIKE %:nganh%", nativeQuery = true)
	List<ThongTinSuDungEntity> findByMaTVNganhContaining(@Param("nganh") String nganh);
}
