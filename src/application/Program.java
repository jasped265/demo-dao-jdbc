/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package application;

import db.Db;
import java.time.LocalDate;
import java.util.List;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.enteties.Department;
import model.enteties.Seller;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author jaspe
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       SellerDao sellerDao = DaoFactory.createSellerDao(); 
       LocalDate lcd = LocalDate.parse("2003-10-18", dtf);
       System.out.println("=== TEST 1: seller findById");
      
       //Seller sl = sellerDao.findById(4);
       
       Department dpt = new Department(1,"Computers");
       /*System.out.println("==== Teste 2: seller findBydepartment ======");
       List<Seller> s = sellerDao.findByDepartment(dpt);*/                
       
      /* System.out.println("==== Teste 2: seller findAll ======");
       /*List<Seller> s = sellerDao.findAll();
       for(Seller sl1: s){
           System.out.println(""+sl1.toString());
       }*/
               
        Seller seller = new Seller(10, "Osvaldo", "osvaPaulo@gamil.com", lcd, 30000.0, dpt); 
        
        //sellerDao.updateSeller(seller);
        sellerDao.deleteById(seller.getId());
        Db.closeConnection();
    }
    
}
