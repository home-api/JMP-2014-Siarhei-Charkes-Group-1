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
            // create unit
            Unit unit = new Unit();
            unit.setName("unit");
            unit.setEmployees(new ArrayList<Employee>());
            service.save(unit);

            // load unit
            Unit foundUnit = service.load(Unit.class, 1);
            System.out.println("Created unit: " + foundUnit);

            // update unit
            foundUnit.setName("newName");
            service.update(foundUnit);

            Address address = new Address();
            address.setStreet("street");

            Employee employee = new Employee();
            employee.setAddress(address);
            service.save(employee);

            // add employee to units
            service.addEmployeeToUnits(employee, Arrays.asList(foundUnit.getId()));
            System.out.println("Unit with added employee: " + foundUnit);
            System.out.println("Employee added to unit: " + employee);

            Project project = new Project();
            project.setName("project");
            service.save(project);
            System.out.println("Created project: " + service.load(Project.class, 1));

            // assign employee to projects
            service.addEmployeeToProjects(employee, Arrays.asList(project.getId()));
            session.refresh(employee);
            System.out.println("Employee added to project: " + employee);
            System.out.println("Project with added employee: " + project);

            // delete unit
            service.delete(foundUnit);
        } finally {
           session.close();
            sessionFactory.close();
        }
    }
}
