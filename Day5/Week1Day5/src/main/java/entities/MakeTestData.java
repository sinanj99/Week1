/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author sinanjasar
 */
public class MakeTestData {
    public static void main(String[] args) {
        EntityManager e = Persistence.createEntityManagerFactory("pu").createEntityManager();
        e.getTransaction().begin();
        e.persist(new BankCustomer("Jens", "Jensen", "123", 10000, 1, "a"));
        e.persist(new BankCustomer("Peter", "Petersen", "456", 20000, 2, "a"));
        e.persist(new BankCustomer("Mogens", "Mogensen", "789", 30000, 3, "a"));
        e.persist(new BankCustomer("Lars", "Larsen", "101112", 40000, 4, "a"));
        e.getTransaction().commit();
    }
}
