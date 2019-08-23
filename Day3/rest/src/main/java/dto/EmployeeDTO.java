/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Employee;

/**
 *
 * @author sinanjasar
 */
public class EmployeeDTO {

    private Long id;
    private String name;
    private String address;

    public EmployeeDTO(Employee e) {
        this.name = e.getName();
        this.address = e.getAddress();
    }
    
}
