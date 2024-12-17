package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
    private String customerID;
    private String name;
    private String phone;
    private Date dob;

    public Customer(String customerID, String name, String phone, Date dob) {
        this.customerID = customerID;
        this.name = name;
        this.phone = phone;
        this.dob = dob;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return String.format("%-6s| %-20s| %-12s| %-11s| %-10s", getCustomerID(),
                getName(), getPhone(), new SimpleDateFormat("dd/MM/yyyy").format(dob), getHomeNetwork());
    }

    public String getHomeNetwork() {
        if (phone.startsWith("098")) return "Viettel";
        else if (phone.startsWith("090")) return "VinaPhone";
        else if (phone.startsWith("093")) return "Mobiphone";
        else return "Unknown";
    }


}
