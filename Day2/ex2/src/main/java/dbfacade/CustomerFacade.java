/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author sinanjasar
 */
public class CustomerFacade {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

    public Customer findByID(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }
    /**
     * løs
     * @param name
     * @return 
     */
    public List<Customer> findByLastName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select customer from Customer customer WHERE customer.lname = :arg", Customer.class);
            query.setParameter("arg", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Long getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q1 = em.createQuery("select count(c) from Customer c");
            return (Long) q1.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q1 = em.createQuery("select customer from Customer customer");
            return q1.getResultList();
        } finally {
            em.close();
        }
    }

    public Customer addCustomer(String fName, String lName) {
        EntityManager em = emf.createEntityManager();
        Customer c = new Customer(fName, lName);
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }
}
