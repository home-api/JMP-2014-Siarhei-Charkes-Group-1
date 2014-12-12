package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Yahor_Karabitsyn on 12/11/2014.
 */
@Entity(name = "unit")
public class Unit {

    private Integer id;
    private String name;

    private List<Employee> employees;

    public Unit() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @OneToMany(targetEntity = Employee.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Unit: {id = " + id + ", name = " + name + ", employees " + employees + "}";
    }
}
