package com.ecommerce.entity;


import javax.persistence.*;

@Entity
@Table(name="Role_ecommerce")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String role;

    public void setRole(String role)
    {
        this.role=role;
    }

    public String getRole()
    {
        return role;
    }
}
