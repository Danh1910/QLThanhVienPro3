package com.example.QLThanhVien.Repository;


import com.example.QLThanhVien.Enity.XuLyViPhamEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XuLyViPhamRepository extends CrudRepository<XuLyViPhamEntity, Integer> {
}
