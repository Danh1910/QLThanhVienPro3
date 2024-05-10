package com.example.QLThanhVien.Repository;

import com.example.QLThanhVien.Enity.ThongTinSuDungEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThongTinSuDungRepository extends CrudRepository<ThongTinSuDungEntity,Integer> {
}
