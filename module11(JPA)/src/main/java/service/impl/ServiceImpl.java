package service.impl;

import models.Employee;
import models.Project;
import models.Unit;
import service.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Yahor_Karabitsyn on 12/11/2014.
 */
public class ServiceImpl implements Service {

    private Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:jpa.db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    @Override
    public void createEmployee(Employee employee) {

    }

    @Override
    public Employee findEmployee(Integer id) {
        Connection con = getConnection();
        return null;
    }

    @Override
    public void deleteEmployee(Integer id) {

    }

    @Override
    public void addEmployeeToUnit(Employee employee, Unit unit) {

    }

    @Override
    public void assignEmployeeToProject(Employee employee, Project project) {

    }

}
