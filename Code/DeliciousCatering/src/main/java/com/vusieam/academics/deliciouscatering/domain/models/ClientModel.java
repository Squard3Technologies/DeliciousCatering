package com.vusieam.academics.deliciouscatering.domain.models;

import java.sql.Date;

/**
 *
 * @author vusi
 */
public class ClientModel {
    
    private Integer id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private Date lastLogin;

    public ClientModel() {
    }

    public ClientModel(Integer id, String name, String surname, Date dateOfBirth, Date lastLogin) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.lastLogin = lastLogin;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
