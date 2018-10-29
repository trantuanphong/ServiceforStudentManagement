/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Service;
import java.util.List;
import model.ServiceDAO;

/**
 *
 * @author ADMIN
 */
public class RegisterProduct {
    private String providerName;
    private int serviceID;
    private List<Service> serviceList = null;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
    
    public List<Service> getService() throws Exception {
        if (serviceList == null) {
            String query = "SELECT * FROM Service WHERE ProviderName LIKE '" + providerName + "'"
                    + " AND Status LIKE 'Actived'";
            serviceList = new ServiceDAO().selectService(query);
        }
        return serviceList;
    }
}
