/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.BuyBalance;
import com.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class BuyBalanceDAO {

    public List<BuyBalance> selectBuyBalance(String query) throws Exception {
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        List<BuyBalance> a = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int walletid = rs.getInt("WalletID");
            String accName = rs.getString("AccountName");
            String purpose = rs.getString("Purpose");
            int value = rs.getInt("Value");
            Date date = rs.getDate("Date");
            a.add(new BuyBalance(accName, walletid, purpose, value, date));
        }
        rs.close();
        conn.close();
        return a;
    }

    public List<BuyBalance> selectBuyBalanceByName(String accName) throws Exception {
        String query = "select * from BuyBalance where AccountName= '" + accName + "'";
        return selectBuyBalance(query);
    }

    public void insertBuyBalance(String name, int id, String purpose, int value) throws Exception {
        Calendar calendar = Calendar.getInstance();
        java.sql.Date currentdate = new java.sql.Date(calendar.getTime().getTime());
        String query = "insert into BuyBalance values(?,?,?,?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        ps.setInt(2, id);
        ps.setString(3, purpose);
        ps.setInt(4, value);
        ps.setDate(5, currentdate);
        ps.executeUpdate();
        conn.close();
    }
}
