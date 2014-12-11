import models.Employee;
import models.Unit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

/**
 * Created by Yahor_Karabitsyn on 12/11/2014.
 */
public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Unit unit = new Unit();
        unit.setName("unit");
        unit.setEmployees(new ArrayList<Employee>());

        session.save(unit);
        session.getTransaction().commit();
    }

}
