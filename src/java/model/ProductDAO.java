/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Product;
import com.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ProductDAO {

    public List<Product> selectProduct(String query) throws Exception {
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        List<Product> a = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int productID = rs.getInt("ProductID");
            String productName = rs.getString("ProductName");
            int serviceID = rs.getInt("ServiceID");
            String providerName = rs.getString("ProviderName");
            String description = rs.getString("Description");
            int quantity = rs.getInt("Quantity");
            int price = rs.getInt("Price");
            String unit = rs.getString("Unit");
            a.add(new Product(productID, productName, serviceID, providerName, description, quantity, price, unit));
        }
        rs.close();
        conn.close();
        return a;
    }

    public List<Product> selectProductByServiceID(int id) throws Exception {
        String query = "select * from Product where ServiceID =" + id;
        return selectProduct(query);
    }

    public List<Product> selectProductByProviderName(String pName) throws Exception {
        String query = "select * from Product where ProviderName=" + pName;
        return selectProduct(query);
    }

    public List<Product> selectProductByProductID(int pid) throws Exception {
        String query = "select * from Product where ProductID=" + pid;
        return selectProduct(query);
    }

    public void insertProduct(Product a) throws Exception {
        String query = "insert into Product values(?,?,?,?,?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, a.getProductName());
        ps.setInt(2, a.getServiceID());
        ps.setString(3, a.getProviderName());
        ps.setInt(4, a.getQuantity());
        ps.setInt(5, a.getPrice());
        ps.setString(6, a.getUnit());
        ps.executeUpdate();
        conn.close();
    }

    public void insertProduct(String productname, int sid, String providername, String des, int quantity, int price, String unit) throws Exception {
        String query = "insert into Product values(?,?,?,?,?,?,?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, productname);
        ps.setInt(2, sid);
        ps.setString(3, providername);
        ps.setString(4, des);
        ps.setInt(5, quantity);
        ps.setInt(6, price);
        ps.setString(7, unit);
        ps.setString(8, "");
        ps.executeUpdate();
        conn.close();
    }

    public void setQuantity(int productID, int i) throws Exception {
        String query = "UPDATE Product SET Quantity = " + i + " WHERE ProductID = " + productID;
        Connection conn = new DBContext().getConnection();
        conn.prepareStatement(query).executeUpdate();
    }

    public void updateProduct(int id, String name, int price, int quantity, String description) throws Exception {
        String query = "UPDATE Product SET ProductName = '" + name + "' , Price = " + price + " , Quantity = " + quantity + " , Description = '" + description + "' WHERE ProductID = " + id;
        System.out.println(query);
        Connection conn = new DBContext().getConnection();
        conn.prepareStatement(query).executeUpdate();
        conn.close();
    }
}
