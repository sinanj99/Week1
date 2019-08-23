/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author sinanjasar
 */
public class CustomerFacadeTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static CustomerFacade facade = CustomerFacade.getFacadeExample(emf);

    public CustomerFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        EntityManager e = emf.createEntityManager();
        e.getTransaction().begin();
        e.persist(new BankCustomer("Jens", "Jensen", "123", 10000, 1, "a"));
        e.persist(new BankCustomer("Peter", "Petersen", "456", 20000, 2, "a"));
        e.persist(new BankCustomer("Mogens", "Mogensen", "789", 30000, 3, "a"));
        e.persist(new BankCustomer("Lars", "Larsen", "101112", 40000, 4, "a"));
        e.getTransaction().commit();
    }

    @Test
    public void testGetCustomerByID() {
        String exp = "Jens Jensen";
        String act = facade.getCustomerByID(Long.valueOf(1)).getFullName();
        assertEquals(exp, act);
    }

    @Test
    public void testGetCustomerByName() {
        String exp = "Mogens Mogensen";
        String act = facade.getCustomerByName("Mogens").get(0).getFullName();
        assertEquals(exp, act);
    }

    @Test
    public void testAddCustomer() {
        String exp = "Hans Hansen";
        facade.addCustomer(new BankCustomer("Hans", "Hansen", "666", 10, 1234, "a"));
        String act = facade.getCustomerByName("Hans").get(0).getFullName();
        assertEquals(exp, act);
    }

    @Test
    public void testGetAllBankCustomers() {
        int exp = 5;
        int act = facade.getAllBankCustomers().size();
        assertEquals(exp, act);
    }

}
