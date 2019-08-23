package facades;

import dto.EmployeeDTO;
import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee getEmployeeById(Long id) {
        return getEntityManager().find(Employee.class, id);
    }

    public List<Employee> getEmployeesByName(String name) {
        TypedQuery<Employee> q = getEntityManager().createQuery("select e from Employee e where e.name = :arg", Employee.class);
        q.setParameter("arg", name);
        return q.getResultList();
    }

    public List<Employee> getAllEmployees() {
        TypedQuery<Employee> q = getEntityManager().createQuery("select e from Employee e", Employee.class);
        return q.getResultList();
    }

    public List<Employee> getEmployeesWithHighestSalary() {
        TypedQuery<Employee> q = getEntityManager().createQuery("select e from Employee e order by e.salary desc", Employee.class);
        return q.setMaxResults(5).getResultList();
    }

    public void createEmployee(String name, String address, int salary) {
        getEntityManager().persist(new Employee(name, address, salary));
    }

}
