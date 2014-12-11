package service;

import models.Employee;
import models.Project;
import models.Unit;

/**
 * Created by Yahor_Karabitsyn on 12/11/2014.
 */
public interface Service {

    void createEmployee(Employee employee);

    Employee findEmployee(Integer id);

    void deleteEmployee(Integer id);

    void addEmployeeToUnit(Employee employee, Unit unit);

    void assignEmployeeToProject(Employee employee, Project project);

}
