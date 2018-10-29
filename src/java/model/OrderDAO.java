/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Order;
import com.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class OrderDAO {

    public List<Order> selectOrder(String query) throws Exception {
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        List<Order> a = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int orderID = rs.getInt("OrderID");
            int serviceID = rs.getInt("ServiceID");
            String providerName = rs.getString("ProviderName");
            int productID = rs.getInt("ProductID");
            String username = rs.getString("UserName");
            String destination = rs.getString("Destination");
            int price = rs.getInt("Price");
            int quantity = rs.getInt("Quantity");
            Date startDate = rs.getDate("StartDate");
            Date endDate = rs.getDate("EndDate");
            String status = rs.getString("Status");
            a.add(new Order(orderID,serviceID, providerName, productID, username, destination, price, quantity, startDate, endDate, status));
        }
        rs.close();
        conn.close();
        return a;
    }
    public List<Order> selectOrderByProviderName(String pName) throws Exception {
        String query = "select * from [Order] where ProviderName= '" + pName+"'";
        return selectOrder(query);
    }

    public List<Order> selectOrderByServiceID(int sid) throws Exception {
        String query = "select * from [Order] where ServiceID=" + sid;
        return selectOrder(query);
    }

    public List<Order> selectOrderByUserName(String userName) throws Exception {
        String query = "select * from [Order] where UserName = '" + userName+"'";
        return selectOrder(query);
    }

    public void setOrderStatus(int oid, String status) throws Exception {
        String query = "update [Order] set Status=? where OrderID=?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        System.out.println("hihi");
        ps.setString(1, status);
        ps.setInt(2, oid);
        ps.executeUpdate();
        conn.close();
    }

    public void insertOrder(Order a) throws Exception {
        String query = "insert into [Order] values(?,?,?,?,?,?,?,?,?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, a.getServiceID());
        ps.setString(2, a.getProviderName());
        ps.setInt(3, a.getProductID());
        ps.setString(4, (a.getUserName()));
        ps.setString(5, (a.getDestination()));
        ps.setInt(6, a.getPrice());
        ps.setInt(7, a.getQuantity());
        ps.setDate(8, new java.sql.Date(a.getStartDate().getTime()));
        ps.setDate(9, new java.sql.Date(a.getEndDate().getTime()));
        ps.setString(10, a.getStatus());
        ps.executeUpdate();
        conn.close();
    }
}
