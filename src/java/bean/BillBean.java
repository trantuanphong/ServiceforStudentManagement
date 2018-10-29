/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Account;
import entity.Bill;
import entity.Service;
import java.io.Serializable;
import java.util.List;
import model.BillDAO;
import model.ServiceDAO;

/**
 *
 * @author Phong
 */
public class BillBean implements Serializable {

    private Account account;
    private int serviceID = 0;
    private List<Service> serviceList = null;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public List<Bill> getBill() throws Exception {
        return new BillDAO().selectBillByUserName(account.getAccountName());
    }

    public List<Service> getServiceList() throws Exception {
        if (serviceList == null) {
            String query;
            if (account.getType() == 2) {
                query = "SELECT * FROM [Service] WHERE ProviderName LIKE '" + account.getAccountName() + "'";
            } else {
                query = "SELECT s.* FROM [Order] o LEFT JOIN [Service] s"
                        + " ON o.ServiceID = s.ServiceID"
                        + " WHERE o.UserName LIKE '" + account.getAccountName() + "'"
                        + " AND o.Status LIKE 'In-Use'";
            }
            serviceList = new ServiceDAO().selectService(query);
        }
        return serviceList;
    }

    public String getServiceName() throws Exception {
        if (serviceID == 0) {
            serviceID = getServiceList().get(0).getServiceID();
        }
        String query = "SELECT * FROM Service WHERE ServiceID = " + serviceID 
                + " AND Status LIKE 'Actived'";
        return new ServiceDAO().selectService(query).get(0).getServiceName();
    }
}
