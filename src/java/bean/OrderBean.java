/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Account;
import entity.Order;
import entity.Service;
import java.io.Serializable;
import java.util.List;
import model.OrderDAO;
import model.ProductDAO;
import model.ServiceDAO;

/**
 *
 * @author Phong
 */
public class OrderBean implements Serializable {

    private Account account;
    private int productID, serviceID;
    private String selectType = "all";

    public Account getAccount() {
        return account;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getProductName() throws Exception {
        return new ProductDAO().selectProductByProductID(productID).get(0).getProductName();
    }

    public List<Service> getServiceName() throws Exception {
        String query = "SELECT * FROM Service WHERE ServiceID = " + serviceID
                + " AND Status LIKE 'Actived'";
        return new ServiceDAO().selectService(query);
    }

    public List<Order> getOrder() throws Exception {
        String query;
        if (account.getType() == 2) {
            query = "select * from [Order] where ProviderName = '" + account.getAccountName() + "'";
        } else {
            query = "select * from [Order] where UserName = '" + account.getAccountName() + "'";
        }
        if (!selectType.equals("all")) {
            query += " AND Status LIKE '" + selectType + "'";
        }
        return new OrderDAO().selectOrder(query);
    }

}
