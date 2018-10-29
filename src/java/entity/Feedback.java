/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Feedback implements Serializable {

    private int id;
    private String receiverName;
    private String senderName;
    private String title;
    private String detail;
    private Date date;
    private String status;

    public Feedback() {
    }

    public Feedback(int id, String receiverName, String senderName, String title, String detail, Date date, String status) {
        this.id = id;
        this.receiverName = receiverName;
        this.senderName = senderName;
        this.title = title;
        this.detail = detail;
        this.date = date;
        this.status = status;
    }

    public Feedback(String receiverName, String senderName, String title, String detail, Date date, String status) {
        this.receiverName = receiverName;
        this.senderName = senderName;
        this.title = title;
        this.detail = detail;
        this.date = date;
        this.status = status;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
