/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.vusieam.academics.deliciouscatering.domain.controllers;

import com.google.gson.Gson;
import com.vusieam.academics.deliciouscatering.data.ClientsDao;
import com.vusieam.academics.deliciouscatering.domain.models.ClientDetails;
import com.vusieam.academics.deliciouscatering.domain.models.GenericResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author vusi
 */
public class CreateAccountController extends HttpServlet {

    private final ClientsDao dao = new ClientsDao();

    

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
            ClientDetails client = new ClientDetails();
            client.setTittle(request.getParameter("txtTitle"));
            client.setName(request.getParameter("txtFirstName"));
            client.setSurname(request.getParameter("txtSurname"));
            client.setDateOfBirth(java.sql.Date.valueOf(request.getParameter("txtDateOfBirth")));
            client.setGender(request.getParameter("txtGender"));
            client.setEmail(request.getParameter("txtEmail"));
            client.setCell(request.getParameter("txtCell"));
            client.setTel(request.getParameter("txtTelNo"));
            client.setPasscode(request.getParameter("txtPassword"));
            client.setSecuredPassword(request.getParameter("txtPassword"));

            var createResponse = dao.createAccount(client);

            String json = new Gson().toJson(createResponse);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } 
        catch (Exception ex) {
            GenericResponse<ClientDetails> createResponse = new GenericResponse();
            createResponse.setStatus(false);
            createResponse.setCode(500);
            createResponse.setMessage(ex.getMessage() + "<br/>" + ex.getStackTrace());
            String json = new Gson().toJson(createResponse);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
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
