/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;

import java.sql.Statement;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    public void InsertSeller(Seller sl) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try{
            pst = conn.prepareStatement(
                "INSERT INTO seller "
                +"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                +"VALUES (?, ? ,? , ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            
           pst.setString(1,sl.getName());
           pst.setString(2,sl.getEmail());
           pst.setDate(3, java.sql.Date.valueOf(sl.getBirthDate()));
           pst.setDouble(4, sl.getSalary());
           pst.setInt(5, sl.getDepartment().getId());
           
          int rowsAffected = pst.executeUpdate();
          System.out.println("RowsAffected: "+rowsAffected);
          if(rowsAffected > 0){
              rs = pst.getGeneratedKeys();
              if(rs.next()){
                  int id = rs.getInt(1);
                  sl.setId(id);
              }
          }else{
              throw new DbException("Erro inesperado nenhuma linha foi afetada");
          }
          
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            Db.closeStatement(pst);
            Db.closeResultSet(rs);
        }
        
    }

    @Override
    public void updateSeller(Seller sl) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try{
            pst = conn.prepareStatement(
                "Update seller "
                +"Set Name = ?, Email = ?, BirthDate = ?, "
                +"BaseSalary = ?, DepartmentId = ? "
                +"WHERE Id = ?"
            );
            
           pst.setString(1,sl.getName());
           pst.setString(2,sl.getEmail());
           pst.setDate(3, java.sql.Date.valueOf(sl.getBirthDate()));
           pst.setDouble(4, sl.getSalary());
           pst.setInt(5, sl.getDepartment().getId());
           pst.setInt(6, sl.getId());
          
           pst.executeUpdate();
           
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            Db.closeStatement(pst);
            Db.closeResultSet(rs);
        }
        
    }

    @Override
    public void deleteById(int id ) {
        PreparedStatement pst = null;
        
        try{
            pst = conn.prepareStatement(
                "Delete From seller WHERE Id = ?"
            );
            pst.setInt(1, id);
            pst.executeUpdate();
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            Db.closeStatement(pst);
        }
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
            
            Department dp = instantiateDepartment(rs);           
            Seller sl = instantiateSeller(rs, dp);
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
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Seller> ls = new ArrayList<>();
        try{
           pst = conn.prepareStatement(
                   "SELECT s.*, d.Name as depName "
                 + "From seller s "
                 + "INNER JOIN Department d "
                 + "ON d.Id = s.departmentId "
                 + "ORDER BY NAME"
           );
                   
           rs = pst.executeQuery();
           Map<Integer, Department> map = new HashMap<>(); 
           while(rs.next()){
                Department dep = map.get(rs.getInt("DepartmentId"));
                   if(dep == null) {
                        dep = instantiateDepartment(rs);
                        map.put(rs.getInt("DepartmentId"), dep);
                   }
                 
                   ls.add(this.instantiateSeller(rs, dep));
           }
           
           return ls;
        }catch(SQLException ex){
            throw new DbException(ex.getMessage());
        }finally{
            Db.closeResultSet(rs);
            Db.closeStatement(pst);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
            Department dp = new Department();

            dp.setId(rs.getInt("DepartmentId"));
            dp.setName(rs.getString("depName"));
            return dp;
    }
    
     private Seller instantiateSeller(ResultSet rs, Department dp) throws SQLException {
            Seller sl = new Seller();
            sl.setBirthDate(rs.getObject("BirthDate", LocalDate.class));
            sl.setDepartment(dp);
            sl.setEmail(rs.getString("Email"));
            sl.setId(rs.getInt("Id"));
            sl.setName(rs.getString("Name"));
            sl.setSalary(rs.getDouble("BaseSalary"));
            return sl;
    }

    @Override
    public List<Seller> findByDepartment(Department dp) {
            ResultSet rs = null;
            PreparedStatement pst = null;
            List<Seller> seller = new ArrayList<>();
            
           try{
              pst = conn.prepareStatement(
                       "SELECT s.*, d.Name as depName "
                       +"From seller s "
                       +"INNER JOIN department d "
                       +"ON s.departmentId = d.Id "
                       +"WHERE s.departmentId = ? "
                       +"ORDER BY depName"
               );
               Map<Integer, Department> map = new HashMap<>(); 
               pst.setInt(1, dp.getId());
               rs = pst.executeQuery();
               while(rs.next()){
                   
                   Department dep = map.get(rs.getInt("DepartmentId"));
                   if(dep == null) {
                        dep = instantiateDepartment(rs);
                        map.put(rs.getInt("DepartmentId"), dep);
                   }
                 
                   seller.add(this.instantiateSeller(rs, dep));
               }
               return seller;
           }catch(SQLException e){
              throw new DbException(e.getMessage());
           }finally{
               Db.closeResultSet(rs);
               Db.closeStatement(pst);
           }
    }
}
