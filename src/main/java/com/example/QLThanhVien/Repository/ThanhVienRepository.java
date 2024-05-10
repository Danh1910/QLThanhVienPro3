package com.example.QLThanhVien.Repository;
import com.example.QLThanhVien.Enity.ThanhVienEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author ASUS
 */

@Repository
public interface ThanhVienRepository extends CrudRepository<ThanhVienEntity,Long>{
    @Query("SELECT ttsd.id FROM ThongTinSuDungEntity ttsd WHERE ttsd.MaTV.MaTV = :id AND ttsd.TGVao IS NOT NULL")
    List<Integer> getIDByMaTV(Integer id);
}
