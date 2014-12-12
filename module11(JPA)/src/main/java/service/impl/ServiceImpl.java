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
    public Employee findEmployee(Integer id) {
        return (Employee) session.load(Employee.class, id);
    }

    @Override
    public Unit findUnit(Integer id) {
        return (Unit) session.load(Unit.class, id);
    }

    @Override
    public List<Unit> getAllUnits() {
        return session.createQuery("SELECT u from unit u").list();
    }

    @Override
    public void save(Object object) {
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
    }

    @Override
    public void update(Object object) {
        session.update(object);
    }

    @Override
    public void deleteUnit(Unit unit) {
        session.delete(unit);
    }

    @Override
    public Project getProject(Integer id) {
        return (Project) session.load(Project.class, id);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
