/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.DBContext;
import entity.Account;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import model.AccountDAO;

/**
 *
 * @author Phong
 */
public class ListAccountBean implements Serializable {

    private String type;
    private String status;

    private int page; //page positon
    private final int PAGE_SIZE = 5; //how many record in the page

    public ListAccountBean() {
        this.type = "0";
        this.status = "All";
        this.page = 1;
    }

    public int getPAGE_SIZE() {
        return PAGE_SIZE;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQueryAccount() {
        String query = "SELECT * FROM Account";
        if (!type.equals("0")) {
            query = query + " WHERE Type = " + type;
        }
        if (!status.equals("All")) {
            if (type.equals("0")) {
                query = query + " WHERE Status LIKE '" + status + "'";
            } else {
                query = query + " AND Status LIKE '" + status + "'";
            }
        }
        return query;
    }

    public int getTotalRows() throws Exception {
        Connection conn = new DBContext().getConnection();
        String query = "SELECT COUNT(*) FROM ("+getQueryAccount()+") a";
        ResultSet rs = conn.prepareStatement(query).executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        conn.close();
        return count;
    }

    public List<Account> getAccount() throws Exception {
        int from = (page - 1) * PAGE_SIZE + 1;
        int to = page * PAGE_SIZE;

        String query = "SELECT * FROM \n"
                + "(SELECT *, ROW_NUMBER() OVER (ORDER BY DateCreated) as row \n"
                + "FROM (" + getQueryAccount() + ") b\n"
                + ") a WHERE a.row BETWEEN " + from + " AND " + to;
        return new AccountDAO().selectAccount(query);
    }

    public int getNumberOfPage() throws Exception {
        int rows = getTotalRows();
        return Math.round(rows / (PAGE_SIZE)) ;
    }
}
