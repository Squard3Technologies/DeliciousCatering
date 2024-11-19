/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vusieam.academics.deliciouscatering.domain.models;

import java.sql.Date;

/**
 *
 * @author vusi
 */
public class BookingPaymentModel {
    
    private Integer bookingId;
    private Date paymentDate;
    private Double paymentAmount;
    private String clientComments = "Bank deposit";

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getClientComments() {
        return clientComments;
    }

    public void setClientComments(String clientComments) {
        this.clientComments = clientComments;
    }
    
}
