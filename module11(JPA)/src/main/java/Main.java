import models.Address;
import models.Employee;
import models.Project;
import models.Unit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import service.Service;
import service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Yahor_Karabitsyn on 12/11/2014.
 */
public class Main {

    public static void main(String[] args) {
        Service service = new ServiceImpl();
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        service.setSession(session);

        try {
            Unit unit = new Unit();
            unit.setId(1);
            unit.setName("unit");
            unit.setEmployees(new ArrayList<Employee>());
            service.save(unit);

            Unit foundUnit = service.findUnit(1);
            System.out.println(foundUnit);

            foundUnit.setName("newName");
            service.update(foundUnit);

            Address address = new Address();
            address.setStreet("street");
            service.save(address);

            Employee employee = new Employee();
            employee.setAddress(address);
            service.save(employee);

            foundUnit.setEmployees(Arrays.asList(employee));
            service.update(unit);
            System.out.println(foundUnit);
            System.out.println(employee);

            Project project = new Project();
            project.setName("project");
            service.save(project);
            System.out.println(project);

            employee.setProjects(Arrays.asList(project));

            service.update(employee);
            System.out.println(employee);

            project = service.getProject(1);
            System.out.println(project);

            service.deleteUnit(foundUnit);
        } finally {
           session.close();
            sessionFactory.close();
        }
    }
}
