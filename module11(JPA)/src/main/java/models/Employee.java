package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by Yahor_Karabitsyn on 12/11/2014.
 */
@Entity
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

    @OneToOne(mappedBy = "employee")
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
}
