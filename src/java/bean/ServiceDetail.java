/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Product;
import entity.Service;
import entity.ServiceReview;
import java.io.Serializable;
import java.util.List;
import model.ProductDAO;
import model.ServiceDAO;
import model.ServiceReviewDAO;

/**
 *
 * @author Phong
 */
public class ServiceDetail implements Serializable {

    private int serviceID;
    private String username;
    private ServiceReview rv = null;

    public ServiceDetail() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public ServiceReview getRv() throws Exception {
        if (rv == null) {
            rv = new ServiceReviewDAO().selectServiceReviewByServiceIDAndUser(serviceID, username).get(0);
        }
        return rv;
    }

    public void setRv(ServiceReview rv) {
        this.rv = rv;
    }

    public List<ServiceReview> getServiceReview() throws Exception {
        return new ServiceReviewDAO().selectServiceReviewByServiceID(serviceID);
    }

    public List<Product> getProduct() throws Exception {
        return new ProductDAO().selectProductByServiceID(serviceID);
    }
    
    public String getServiceInfor() throws Exception {
        Service service = new ServiceDAO().selectService("SELECT * FROM Service WHERE ServiceID = "+ serviceID).get(0);
        return service.getServiceName() + " - " + service.getProviderName();
    }
    
    public int getRatePoint() throws Exception {
        String query = "SELECT ServiceID, SUM(Rating) FROM ServiceReview GROUP BY ServiceID HAVING ServiceID = " + serviceID;
        List<ServiceReview> list = new ServiceReviewDAO().selectServiceReview(query);
        if (list.isEmpty()) return 0;
        else return list.get(0).getRating();
    }
}
