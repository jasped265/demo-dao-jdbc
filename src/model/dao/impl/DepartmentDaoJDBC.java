/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;

import db.Db;
import db.DbException;
import java.util.List;
import model.dao.DepartmentDao;
import model.enteties.Department;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author jaspe
 */
public class DepartmentDaoJDBC implements DepartmentDao {
    private final Connection conn;
    
    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void InsertDepartment(Department dep) {
      PreparedStatement pst = null;
      ResultSet rs = null;
        try{
            pst = conn.prepareStatement(
             "INSERT INTO department "
             +"(NAME) "
             +"Values (?)",
             Statement.RETURN_GENERATED_KEYS       
            );
            
            pst.setString(1, dep.getName());
            int rowsAffected = pst.executeUpdate();
            
            if(rowsAffected > 0){
                rs = pst.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    dep.setId(id);
                }
            }
            
        }catch(SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            Db.closeStatement(pst);
            Db.closeResultSet(rs);
        }
    }

    @Override
    public void updateDepartment(Department dep) {
        PreparedStatement pst = null;
        try{
            pst = conn.prepareStatement(
             "UPDATE department "
             +"Set Name = ? "
             +"Where Id = ?"
            );
            
            pst.setString(1, dep.getName());
            pst.setInt(2, dep.getId());
            pst.executeUpdate();
            
        }catch(SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            Db.closeStatement(pst);
        }
    }

    @Override
    public void deleteById(int id) {
        PreparedStatement pst = null;
        try{
            pst = conn.prepareStatement(
             "Delete from department "
            +"Where Id = ?"
            );
            
            pst.setInt(1, id);
            pst.executeUpdate();
  
        }catch(SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            Db.closeStatement(pst);
        }
    }

    @Override
    public Department findById(Department dep) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Department dp = null;
        try{
            pst = conn.prepareStatement(
              "Select d.* "
            + "From department d "
             +"Where Id = ?"
            );
          
            pst.setInt(1, dep.getId());
         
            rs = pst.executeQuery();
            if(rs.next()){
                dp = instantiateDepartment(rs);
                return dp;
            }
         return null;
        }catch(SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            Db.closeStatement(pst);
            Db.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Department> ls = new ArrayList<>();
       
        try{
            pst = conn.prepareStatement(
                "Select d.* "
               +"From department d"    
            );
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                ls.add(instantiateDepartment(rs));
            }
            
           return ls;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    
     private Department instantiateDepartment(ResultSet rs) throws SQLException {
            
            Department dp = new Department();

            dp.setId(rs.getInt("Id"));
            dp.setName(rs.getString("Name"));
            return dp;
    }
}
