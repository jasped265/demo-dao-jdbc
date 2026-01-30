/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package application;

import java.util.ArrayList;
import java.util.List;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.enteties.Department;

/**
 *
 * @author jaspe
 */
public class Program2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Department dp = new Department(3,"Secretaria");
        List<Department> ld = new ArrayList<>();
        //departmentDao.InsertDepartment(dp);
        //departmentDao.updateDepartment(dp);
        //Department d = departmentDao.findById(dp);
        //ld = departmentDao.findAll();
        
        /*for(int j = 24; j > 8; j--){
            departmentDao.deleteById(departmentDao.findById(new Department(j,"Secretaria")).getId());
        }*/
        
    }
    
}
