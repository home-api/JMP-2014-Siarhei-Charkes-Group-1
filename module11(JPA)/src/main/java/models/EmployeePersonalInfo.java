package models;

import models.Employee;

import javax.persistence.*;

/**
 * Created by Yahor_Karabitsyn on 12/11/2014.
 */
@Entity
public class EmployeePersonalInfo {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;

    private Employee employee;

    public EmployeePersonalInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    @OneToOne
    @JoinColumn(name = "employee_id")
    public Employee getEmployee() {
        return employee;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
