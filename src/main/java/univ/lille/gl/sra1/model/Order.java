package univ.lille.gl.sra1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import univ.lille.gl.sra1.dao.Status;

@Entity
@Table(name="orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue
    long id;

    String customerId;

    @Temporal(TemporalType.TIMESTAMP)
    Date createdOn;

    int amount;

    Status currentStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerId(Long aLong) {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public Status getCurrentStatus(String ready_to_deliver) {
        return currentStatus;
    }
    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }


}