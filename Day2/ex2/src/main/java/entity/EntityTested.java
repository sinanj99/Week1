/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dbfacade.CustomerFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sinanjasar
 */
public class EntityTested {

    public static void main(String[] args) {
        Customer c1 = new Customer("sne", "krishna");
        Customer c2 = new Customer("hara", "krishna");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(c1);
        em.persist(c2);
        em.getTransaction().commit();
        CustomerFacade cf = new CustomerFacade();
        System.out.println(cf.findByID(Long.valueOf(1)).getLname());
        System.out.println(cf.findByLastName("krishna"));
        System.out.println(cf.getNumberOfCustomers());
        System.out.println(cf.allCustomers());
        System.out.println(cf.addCustomer("hara", "krishna"));

    }
}
