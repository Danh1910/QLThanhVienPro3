/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.QLThanhVien.Repository;

import com.example.QLThanhVien.Enity.ThanhVienEnity;
import org.springframework.data.repository.CrudRepository;
/**
 *
 * @author ASUS
 */

public interface ThanhVienRepository extends CrudRepository<ThanhVienEnity, Long> {
    // Tạo một phương thức truy vấn để tìm thành viên bằng mã thành viên
//    ThanhVienEnity findByMaTV(Integer maTV);
}
