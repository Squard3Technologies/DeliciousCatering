
package com.vusieam.academics.deliciouscatering.domain.models;

import java.sql.Date;


/**
 *
 * @author vusi
 */
public class ClientDetails {    
    private Integer id = -1;
    private String tittle;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String Gender;
    private String email;
    private String cell;
    private String tel;
    private String fax;   
    private AddressModel address;
    private String passcode;   
    private String securedPassword;   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }   

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getSecuredPassword() {
        return securedPassword;
    }

    public void setSecuredPassword(String securedPassword) {
        this.securedPassword = securedPassword;
    }
}
