/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package application;

import db.Db;
import java.time.LocalDate;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.enteties.Department;
import model.enteties.Seller;

/**
 *
 * @author jaspe
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Department obj = new Department(1, "RH");
       Seller seller = new Seller(21, "Bob", "bob@gmail.com", LocalDate.now(), 3000.0, obj);
       SellerDao sellerDao = DaoFactory.createSellerDao(); 
       
       Seller sl = sellerDao.findById(4);
       System.out.println(sl);
       
       Db.closeConnection();
    }
    
}
