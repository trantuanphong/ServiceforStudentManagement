/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Wallet;
import com.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class WalletDAO {

    public List<Wallet> selectWallet(String query) throws Exception {
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        List<Wallet> list = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int walletID = rs.getInt("WalletID");
            int balance = rs.getInt("Balance");
            java.util.Date dateCreated = rs.getDate("DateCreated");
            list.add(new Wallet(walletID, balance, dateCreated));
        }
        rs.close();
        conn.close();
        return list;
    }
    
    public List<Wallet> selectWalletByID(int id) throws Exception {
        String query = "SELECT * FROM Wallet WHERE WalletID = " + id;
        return selectWallet(query);
    }

    public void insertWallet(Wallet a) throws Exception {
        String query = "insert into Wallet values(?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, a.getBalance());
        ps.setDate(2, (Date) a.getDateCreated());
        ps.executeUpdate();
        conn.close();
    }

    public void setBalance(int walletid, int newbalance) throws Exception {
        String query = "update Wallet set Balance=? where WalletID=?";
        Connection conn = new DBContext().getConnection();
        System.out.println(walletid);
        PreparedStatement ps = conn.prepareStatement(query);
        System.out.println(newbalance);
        ps.setInt(1, newbalance);
        ps.setInt(2, walletid);
        ps.executeUpdate();
        conn.close();
    }

    public void insertWallet() throws Exception {
        String query = "insert into Wallet values(?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, 0);
        Calendar calendar = Calendar.getInstance();
        java.sql.Date currentdate = new java.sql.Date(calendar.getTime().getTime());
        ps.setString(2, currentdate.toString());
        ps.executeUpdate();
        conn.close();

    }

    public int getNextWalletID() throws Exception {
        String query = "select MAX(Wallet.WalletID) as 'WalletID' from Wallet ";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        int id = 1;
        while (rs.next()) {
            id = rs.getInt("WalletID");
        }
        conn.close();
        return id;
    }
}
