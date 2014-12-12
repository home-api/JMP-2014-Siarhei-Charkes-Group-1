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

    Employee findEmployee(Integer id);

    Unit findUnit(Integer id);

    List<Unit> getAllUnits();

    void save(Object unit);

    void update(Object object);

    void deleteUnit(Unit unit);

    Project getProject(Integer id);

    void setSession(Session session);
}
