/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.vusieam.academics.deliciouscatering.domain.controllers;

import com.google.gson.Gson;
import com.vusieam.academics.deliciouscatering.domain.models.BookingPaymentModel;
import com.vusieam.academics.deliciouscatering.domain.models.ClientModel;
import com.vusieam.academics.deliciouscatering.domain.models.GenericResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author vusi
 */
@WebServlet(name = "manageBookings", urlPatterns = {"/manageBookings"})
public class ManageBookingsController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            GenericResponse<ClientModel> authenticated = new GenericResponse<>();
            HttpSession session = request.getSession(false);
            if (session == null) {
                authenticated.setStatus(false);
                authenticated.setCode(403);
                authenticated.setMessage("Session may have expired, please login.");
            }
            else{
                if (session.getAttribute("clientProfile") == null) {
                    authenticated.setStatus(false);
                    authenticated.setCode(403);
                    authenticated.setMessage("Session may have expired, please login.");
                }
                else {
                    String userJson = (String) session.getAttribute("clientProfile");
                    ClientModel client = new Gson().fromJson(userJson, ClientModel.class);
                    
                    if(client.getRole().equalsIgnoreCase("client")){
                        var transactiontype = request.getParameter("transactionType");
                        if(transactiontype.equalsIgnoreCase("makePayment")){
                            BookingPaymentModel data = new BookingPaymentModel();
                        }
                    }
                    else{
                        
                    }
                    
                    
                    authenticated.setStatus(true);
                    authenticated.setCode(200);
                    authenticated.setMessage("Authenticated");
                    authenticated.setData(client);
                }
            }

        } catch (Exception ex) {

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

        } catch (Exception ex) {

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
