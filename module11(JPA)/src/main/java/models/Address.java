package models;

import javax.persistence.*;

/**
 * Created by Yahor_Karabitsyn on 12/11/2014.
 */
@Embeddable
public class Address {

    private String street;
    private String houseNumber;
    private String phone;

    private Employee employee;

    public Address() {
    }

    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    @Column(name = "house_number")
    public String getHouseNumber() {
        return houseNumber;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    @OneToOne
    @JoinColumn(name = "employee_id")
    public Employee getEmployee() {
        return employee;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
