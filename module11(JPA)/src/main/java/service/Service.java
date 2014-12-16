package service;

import models.Employee;
import models.Project;
import models.Unit;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Yahor_Karabitsyn on 12/11/2014.
 */
public interface Service {

    /* CRUD operations */
    void save(Object object);

    <T> T load(Class<T> clazz, Integer id);

    void delete(Object object);

    void update(Object object);
    /* end CRUD operations */

    List<Unit> getAllUnits();

    void addEmployeeToUnits(Employee employee, List<Integer> unitsIds);

    void addEmployeeToProjects(Employee employee, List<Integer> projectsIds);

    void setSession(Session session);
}
