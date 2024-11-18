/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vusieam.academics.deliciouscatering.domain.models;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author vusi
 */
public class BookingModel {
    
    private Integer id;
    private Integer typeofEvent;
    private Date eventDate;
    private Time eventTime;
    private Integer attendingAdults = 0;
    private Integer attendingKids = 0;
    private String emailAddress = "";
    private String cellMobile = "";
    private String telNo = "";
    private Integer addressType = 0;
    private String streetNo = "";
    private String streetName = "";
    private String complexName = "";
    private String suburb = "";
    private String city = "";
    private String zipCode = "";
    private String province = "";
    private String country = "";
    
    //Menus
    private Boolean adultMenuTacos;
    private Boolean adultMenuChickenWrap;
    private Boolean adultMenuChickenKebab;
    private Boolean kidsMenuMiniPizzaCheese;
    private Boolean kidsMenuMiniMiniPizza;
    private Boolean kidsMenuMiniSliders;
    private Boolean kidsMenuMiniHandpie;
    
    //Drinks
    private Boolean menuDrinksIcetea;
    private Boolean menuDrinksOrangeJuice;
    private Boolean menuDrinksAppleJuice;
    private Boolean menuDrinksFantaOrange;
    private Boolean menuDrinksCocacola;
    private Boolean menuDrinksApricotJuice;
    
    //Dessert
    private Boolean menuDessertOreoPudding;
    private Boolean menuDessertOreoBalls;
    private Boolean menuDessertChurros;
    private Boolean menuDessertDonuts;
    private Boolean menuDessertMalva;
    private Boolean menuDessertBerry;
    
    
    private Boolean decorNeeded;
    private String themeDetails;
    
    private String totalCost;
    private String stage;
    private Date creationDate;

    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeofEvent() {
        return typeofEvent;
    }

    public void setTypeofEvent(Integer typeofEvent) {
        this.typeofEvent = typeofEvent;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public Integer getAttendingAdults() {
        return attendingAdults;
    }

    public void setAttendingAdults(Integer attendingAdults) {
        this.attendingAdults = attendingAdults;
    }

    public Integer getAttendingKids() {
        return attendingKids;
    }

    public void setAttendingKids(Integer attendingKids) {
        this.attendingKids = attendingKids;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCellMobile() {
        return cellMobile;
    }

    public void setCellMobile(String cellMobile) {
        this.cellMobile = cellMobile;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public Integer getAddressType() {
        return addressType;
    }

    public void setAddressType(Integer addressType) {
        this.addressType = addressType;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getComplexName() {
        return complexName;
    }

    public void setComplexName(String complexName) {
        this.complexName = complexName;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getAdultMenuTacos() {
        return adultMenuTacos;
    }

    public void setAdultMenuTacos(Boolean adultMenuTacos) {
        this.adultMenuTacos = adultMenuTacos;
    }

    public Boolean getAdultMenuChickenWrap() {
        return adultMenuChickenWrap;
    }

    public void setAdultMenuChickenWrap(Boolean adultMenuChickenWrap) {
        this.adultMenuChickenWrap = adultMenuChickenWrap;
    }

    public Boolean getAdultMenuChickenKebab() {
        return adultMenuChickenKebab;
    }

    public void setAdultMenuChickenKebab(Boolean adultMenuChickenKebab) {
        this.adultMenuChickenKebab = adultMenuChickenKebab;
    }

    public Boolean getKidsMenuMiniPizzaCheese() {
        return kidsMenuMiniPizzaCheese;
    }

    public void setKidsMenuMiniPizzaCheese(Boolean kidsMenuMiniPizzaCheese) {
        this.kidsMenuMiniPizzaCheese = kidsMenuMiniPizzaCheese;
    }

    public Boolean getKidsMenuMiniMiniPizza() {
        return kidsMenuMiniMiniPizza;
    }

    public void setKidsMenuMiniMiniPizza(Boolean kidsMenuMiniMiniPizza) {
        this.kidsMenuMiniMiniPizza = kidsMenuMiniMiniPizza;
    }

    public Boolean getKidsMenuMiniSliders() {
        return kidsMenuMiniSliders;
    }

    public void setKidsMenuMiniSliders(Boolean kidsMenuMiniSliders) {
        this.kidsMenuMiniSliders = kidsMenuMiniSliders;
    }

    public Boolean getKidsMenuMiniHandpie() {
        return kidsMenuMiniHandpie;
    }

    public void setKidsMenuMiniHandpie(Boolean kidsMenuMiniHandpie) {
        this.kidsMenuMiniHandpie = kidsMenuMiniHandpie;
    }

    public Boolean getMenuDrinksIcetea() {
        return menuDrinksIcetea;
    }

    public void setMenuDrinksIcetea(Boolean menuDrinksIcetea) {
        this.menuDrinksIcetea = menuDrinksIcetea;
    }

    public Boolean getMenuDrinksOrangeJuice() {
        return menuDrinksOrangeJuice;
    }

    public void setMenuDrinksOrangeJuice(Boolean menuDrinksOrangeJuice) {
        this.menuDrinksOrangeJuice = menuDrinksOrangeJuice;
    }

    public Boolean getMenuDrinksAppleJuice() {
        return menuDrinksAppleJuice;
    }

    public void setMenuDrinksAppleJuice(Boolean menuDrinksAppleJuice) {
        this.menuDrinksAppleJuice = menuDrinksAppleJuice;
    }

    public Boolean getMenuDrinksFantaOrange() {
        return menuDrinksFantaOrange;
    }

    public void setMenuDrinksFantaOrange(Boolean menuDrinksFantaOrange) {
        this.menuDrinksFantaOrange = menuDrinksFantaOrange;
    }

    public Boolean getMenuDrinksCocacola() {
        return menuDrinksCocacola;
    }

    public void setMenuDrinksCocacola(Boolean menuDrinksCocacola) {
        this.menuDrinksCocacola = menuDrinksCocacola;
    }

    public Boolean getMenuDrinksApricotJuice() {
        return menuDrinksApricotJuice;
    }

    public void setMenuDrinksApricotJuice(Boolean menuDrinksApricotJuice) {
        this.menuDrinksApricotJuice = menuDrinksApricotJuice;
    }
    
    public Boolean getMenuDessertOreoPudding() {
        return menuDessertOreoPudding;
    }

    public void setMenuDessertOreoPudding(Boolean menuDessertOreoPudding) {
        this.menuDessertOreoPudding = menuDessertOreoPudding;
    }

    public Boolean getMenuDessertOreoBalls() {
        return menuDessertOreoBalls;
    }

    public void setMenuDessertOreoBalls(Boolean menuDessertOreoBalls) {
        this.menuDessertOreoBalls = menuDessertOreoBalls;
    }

    public Boolean getMenuDessertChurros() {
        return menuDessertChurros;
    }

    public void setMenuDessertChurros(Boolean menuDessertChurros) {
        this.menuDessertChurros = menuDessertChurros;
    }

    public Boolean getMenuDessertDonuts() {
        return menuDessertDonuts;
    }

    public void setMenuDessertDonuts(Boolean menuDessertDonuts) {
        this.menuDessertDonuts = menuDessertDonuts;
    }

    public Boolean getMenuDessertMalva() {
        return menuDessertMalva;
    }

    public void setMenuDessertMalva(Boolean menuDessertMalva) {
        this.menuDessertMalva = menuDessertMalva;
    }

    public Boolean getMenuDessertBerry() {
        return menuDessertBerry;
    }

    public void setMenuDessertBerry(Boolean menuDessertBerry) {
        this.menuDessertBerry = menuDessertBerry;
    }

    public Boolean getDecorNeeded() {
        return decorNeeded;
    }

    public void setDecorNeeded(Boolean decorNeeded) {
        this.decorNeeded = decorNeeded;
    }

    public String getThemeDetails() {
        return themeDetails;
    }

    public void setThemeDetails(String themeDetails) {
        this.themeDetails = themeDetails;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    
}
