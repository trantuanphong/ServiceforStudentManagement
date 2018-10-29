/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Account;
import entity.Feedback;
import entity.Service;
import java.util.List;
import model.FeedbackDAO;
import model.ServiceDAO;

/**
 *
 * @author Phong
 */
public class FeedbackBean {

    private Account account;
    private List<Service> serviceList = null;
    private String selectType = "All";
    
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public List<Service> getServiceList() throws Exception {
        if (serviceList == null) {
            String query = "SELECT s.* FROM \n"
                    + " (SELECT ServiceID FROM [Order] "
                    + " WHERE UserName LIKE '" + account.getAccountName() + "'"
                    + " GROUP BY ServiceID) hihi \n"
                    + " LEFT JOIN [Service] s \n"
                    + " ON hihi.ServiceID = s.ServiceID";
            serviceList = new ServiceDAO().selectService(query);
        }
        return serviceList;
    }

    public List<Feedback> getFeedbackList() throws Exception {
        String receiver = account.getAccountName();
        if (account.getType() == 3) {
            receiver = "pscruby0";
        }
        String query = "SELECT * FROM Feedback WHERE Receiver LIKE '" + receiver + "'";
        if (!selectType.equals("All")) {
            query = query + " AND Status LIKE '" + selectType + "'";
        }
        return new FeedbackDAO().selectFeedback(query);
    }
}
