package service.impl;

import models.Employee;
import models.Project;
import models.Unit;
import org.hibernate.Session;
import service.Service;

import java.util.List;

/**
 * Created by Yahor_Karabitsyn on 12/11/2014.
 */
public class ServiceImpl implements Service {

    private Session session;

    public ServiceImpl() {
    }

    @Override
    public void save(Object object) {
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
    }

    public <T> T load(Class<T> clazz, Integer id) {
        return (T) session.load(clazz, id);
    }

    public void delete(Object object) {
        session.delete(object);
    }

    public void update(Object object) {
        session.update(object);
    }

    @Override
    public List<Unit> getAllUnits() {
        return session.createQuery("SELECT u from unit u").list();
    }

    @Override
    public void addEmployeeToUnits(Employee employee, List<Integer> unitsIds) {
        for (Integer unitId : unitsIds) {
            Unit unit = load(Unit.class, unitId);
            unit.getEmployees().add(employee);
            employee.setUnit(unit);
            save(unit);
            save(employee);
        }
    }

    @Override
    public void addEmployeeToProjects(Employee employee, List<Integer> projectsIds) {
        for (Integer projectId : projectsIds) {
            Project project = load(Project.class, projectId);
            project.getEmployees().add(employee);
            save(project);
        }
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
