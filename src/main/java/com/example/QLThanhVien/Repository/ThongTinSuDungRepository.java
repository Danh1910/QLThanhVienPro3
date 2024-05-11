package com.example.QLThanhVien.Repository;

import com.example.QLThanhVien.Enity.ThongTinSuDungEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
	
	@Query(value = "SELECT * FROM thongtinsd WHERE TGVao IS NOT NULL", nativeQuery = true)
	Iterable<ThongTinSuDungEntity> findTGVao();
	
	@Query(value = "SELECT * FROM thongtinsd WHERE TGMuon IS NOT NULL", nativeQuery = true)
	Iterable<ThongTinSuDungEntity> findTGMuonTra();
	
	@Query(value = "SELECT * FROM thongtinsd WHERE TGDatCho IS NOT NULL", nativeQuery = true)
	Iterable<ThongTinSuDungEntity> findTGDatCho();

	@Query(value = "SELECT tsd.* "
			+ "FROM thongtinsd tsd "
			+ "WHERE tsd.MaTB", nativeQuery = true)
	List<ThongTinSuDungEntity> LayThongTinSuDungTB();
}
