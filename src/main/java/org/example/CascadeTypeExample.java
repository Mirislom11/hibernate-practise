package org.example;

import org.example.entity.Employee;
import org.example.entity.EmployeeTask;
import org.example.entity.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.xml.crypto.dsig.TransformService;

public class CascadeTypeExample {

    public static void main(String[] args) {



        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, "1101");
            employee.setLastName("Elbekjoniiii134");
            employee.getEmployeeTask().setTaskName("ISSSSSSSSS22");
            session.merge(employee);
            transaction.commit();
        }
    }
}

/*Employee employee = session.get(Employee.class, "1451");

            session.remove(employee);
            transaction.commit();*/

/*Employee employee = Employee.builder()
                    .firstName("Sohib")
                    .lastName("Sayfullayev")
                    .build();
            EmployeeTask employeeTask1 = EmployeeTask.builder()
                    .taskName("Ish3")
                    .build();
            employee.setEmployeeTask(employeeTask1);
            employeeTask1.setEmployee(employee);

            session.persist(employee);
            session.flush();*/
