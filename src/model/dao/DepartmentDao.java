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
    void InsertDepartment(Department dep);
    void updateDepartment(Department dep);
    void deleteById(int id);
    Department findById(Department dep); 
    List<Department> findAll();
}
