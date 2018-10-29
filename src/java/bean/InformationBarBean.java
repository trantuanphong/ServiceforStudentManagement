/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Account;
import entity.Service;
import entity.ServiceReview;
import java.io.Serializable;
import java.util.List;
import model.AccountDAO;
import model.ServiceDAO;
import model.ServiceReviewDAO;

/**
 *
 * @author Phong
 */
public class InformationBarBean implements Serializable {

    private int serviceID;
    
    public InformationBarBean() {
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public Service getServiceProviderName() throws Exception {
        return new ServiceDAO().selectServiceByID(serviceID).get(0);
    }
    
    public List<ServiceReview> getTopService() throws Exception {
        String query = "SELECT ServiceID, SUM(Rating) AS Rating FROM ServiceReview GROUP BY ServiceID ORDER BY Rating DESC";
        return new ServiceReviewDAO().selectServiceReviewTop(query);
    }

    public List<Account> getNewAccount() throws Exception {
        String query = "SELECT TOP(5) * FROM Account "
                + " WHERE Type < 3 AND Status LIKE 'Actived'"
                + " ORDER BY DateCreated DESC";
        return new AccountDAO().selectAccount(query);
    }

    public List<Service> getNewService() throws Exception {
        String query = "SELECT TOP(5) * FROM Service WHERE Status LIKE 'Actived' ORDER BY DateCreated DESC";
        return new ServiceDAO().selectService(query);
    }
}
