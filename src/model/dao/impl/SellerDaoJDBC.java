/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;

import db.Db;
import db.DbException;
import java.util.List;
import model.dao.SellerDao;
import model.enteties.Seller;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.enteties.Department;
/**
 *
 * @author jaspe
 */
public class SellerDaoJDBC implements SellerDao {
    private  final Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void voidInsertSeller() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateSeller() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void sellerFindId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            pst = conn.prepareStatement(
                    "SELECT s.*, d.Name as depName "
                    +"From seller s "
                    +"INNER JOIN department d "
                    +"ON d.Id = s.DepartmentId "
                    +"Where s.Id = ?"
            );
            
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            if(!rs.next()){
                return null;
            }
            
            Department dp = new Department();
            dp.setId(rs.getInt("DepartmentId"));
            dp.setName(rs.getString("depName"));
           
            Seller sl = new Seller();
            sl.setBirthDate(rs.getObject("BirthDate", LocalDate.class));
            sl.setDepartment(dp);
            sl.setEmail(rs.getString("Email"));
            sl.setId(rs.getInt("Id"));
            sl.setName(rs.getString("Name"));
            sl.setSalary(rs.getDouble("BaseSalary"));
            
            return sl;
        } catch (SQLException ex) {
           throw new DbException(ex.getMessage());
        }finally{
            Db.closeResultSet(rs);
            Db.closeStatement(pst);
        }
    }

    @Override
    public List<Seller> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
