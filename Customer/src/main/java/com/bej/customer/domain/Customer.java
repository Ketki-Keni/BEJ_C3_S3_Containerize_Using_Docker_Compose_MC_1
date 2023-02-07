/*
 * Author : Ketki Keni
 * Date : 06-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.customer.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    private int customerId;
    private String customerName;
    private String customerPassword;
    private String customerPhoneNo;

    public Customer() {
    }

    public Customer(int customerId, String customerName, String password, String customerPhoneNo) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPassword = password;
        this.customerPhoneNo = customerPhoneNo;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

    public void setCustomerPhoneNo(String customerPhoneNo) {
        this.customerPhoneNo = customerPhoneNo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", password='" + customerPassword + '\'' +
                ", customerPhoneNo='" + customerPhoneNo + '\'' +
                '}';
    }
}
