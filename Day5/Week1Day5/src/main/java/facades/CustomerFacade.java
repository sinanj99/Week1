package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CustomerFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CustomerDTO getCustomerByID(Long id) {
        return new CustomerDTO(getEntityManager().find(BankCustomer.class, id));
    }

    public List<CustomerDTO> getCustomerByName(String name) {
        TypedQuery<BankCustomer> tq = getEntityManager().createQuery("select c "
                + "from BankCustomer c where c.firstName = :fname", BankCustomer.class);
        tq.setParameter("fname", name);
        List<CustomerDTO> dto = new ArrayList();
        for (BankCustomer bc : tq.getResultList()) {
            dto.add(new CustomerDTO(bc));
        }
        return dto;
    }

    public BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return cust;
    }

    public List<BankCustomer> getAllBankCustomers() {
        TypedQuery<BankCustomer> tq = getEntityManager().createQuery("select c "
                + "from BankCustomer c", BankCustomer.class);
        return tq.getResultList();
    }

}
