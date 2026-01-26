/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.dao;

import java.util.List;
import model.enteties.Department;
import model.enteties.Seller;

/**
 *
 * @author jaspe
 */
public interface DepartmentDao {
    void voidInsertDepartment();
    void updateDepartment();
    void deleteById();
    void sellerFindId();
    Department findById(Integer id); 
    List<Department> findAll();
}
