/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.dao;

import java.util.List;
import model.enteties.Seller;

/**
 *
 * @author jaspe
 */
public interface SellerDao {
    void voidInsertSeller();
    void updateSeller();
    void deleteById();
    void sellerFindId();
    Seller findById(Integer id); 
    List<Seller> findAll();
}
