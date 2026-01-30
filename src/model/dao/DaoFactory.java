/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import db.Db;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

/**
 *
 * @author jaspe
 */
public class DaoFactory {
    public static SellerDao createSellerDao(){
       return new SellerDaoJDBC(Db.getConnection());
    }
    
    public static DepartmentDao createDepartmentDao(){
             return new DepartmentDaoJDBC(Db.getConnection());
    }
}
