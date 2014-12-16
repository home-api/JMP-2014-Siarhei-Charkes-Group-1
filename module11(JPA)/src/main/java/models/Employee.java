package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Yahor_Karabitsyn on 12/11/2014.
 */
@Entity(name = "employee")
public class Employee {

    private Integer id;
    private EmployeeStatus employeeStatus;

    private Address address;
    private EmployeePersonalInfo employeePersonalInfo;
    private Unit unit;

    private List<Project> projects;

    public Employee() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    @Enumerated(EnumType.STRING)
    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    @Embedded
    public Address getAddress() {
        return address;
    }

    @OneToOne(mappedBy = "employee")
    public EmployeePersonalInfo getEmployeePersonalInfo() {
        return employeePersonalInfo;
    }

    @ManyToOne(targetEntity = Unit.class)
    @JoinColumn(name = "unit_id")
    public Unit getUnit() {
        return unit;
    }

    @ManyToMany
    @JoinTable(
            name = "employee_projects",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    public List<Project> getProjects() {
        return projects;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmployeePersonalInfo(EmployeePersonalInfo employeePersonalInfo) {
        this.employeePersonalInfo = employeePersonalInfo;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeStatus=" + employeeStatus +
                ", address=" + address +
                ", employeePersonalInfo=" + employeePersonalInfo +
                ", unit=" + unit +
                ", projects=" + projects +
                '}';
    }
}
