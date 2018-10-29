/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.ServiceReview;
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
public class ServiceReviewDAO {

    public List<ServiceReview> selectServiceReview(String query) throws Exception {
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        List<ServiceReview> a = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int serviceID = rs.getInt("ServiceID");
            String username = rs.getString("UserName");
            int rating = rs.getInt("Rating");
            String comment = rs.getString("Comment");
            Date date = rs.getDate("Date");
            String status = rs.getString("Status");
            a.add(new ServiceReview(serviceID, username, rating, comment, date, status));
        }
        rs.close();
        conn.close();
        return a;
    }
    
    public List<ServiceReview> selectServiceReviewTop(String query) throws Exception {
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        List<ServiceReview> a = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int serviceID = rs.getInt("ServiceID");
            int rating = rs.getInt("Rating");
            a.add(new ServiceReview(serviceID, rating));
        }
        rs.close();
        conn.close();
        return a;
    }

    public List<ServiceReview> selectServiceReviewByServiceID(int sid) throws Exception {
        String query = "select * from ServiceReview where ServiceID=" + sid;
        return selectServiceReview(query);
    }
    
    public List<ServiceReview> selectServiceReviewByServiceIDAndUser(int sid, String username) throws Exception {
        String query = "select * from ServiceReview where ServiceID = " + sid 
                + " AND UserName LIKE '" + username + "'";
        return selectServiceReview(query);
    }
    
    public void insertServiceReview(ServiceReview a) throws Exception {
        String query = "insert into ServiceReview values(?,?,?,?,?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, a.getServiceID());
        ps.setString(2, a.getUserName());
        ps.setInt(3, a.getRating());
        ps.setString(4, a.getComment());
        ps.setDate(5, new java.sql.Date(a.getDate().getTime()));
        ps.setString(6, a.getStatus());
        ps.executeUpdate();
        conn.close();
    }

    public void setStatus(int sid, String status) throws Exception {
        String query = "update ServiceReview set Status=? where ServiceID=?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, status);
        ps.setInt(2, sid);
        ps.executeUpdate();
        conn.close();
    }
    
    public void updateReview(ServiceReview serviceReview) throws Exception {
        String query = "UPDATE ServiceReview SET Rating = " + serviceReview.getRating() 
                + " , Comment = '" + serviceReview.getComment() +"' WHERE ServiceID = " 
                + serviceReview.getServiceID() + " AND UserName LIKE '" + serviceReview.getUserName() +"'";
        System.out.println(query);
        Connection conn = new DBContext().getConnection();
        conn.createStatement().executeUpdate(query);
        conn.close();
    }
}
