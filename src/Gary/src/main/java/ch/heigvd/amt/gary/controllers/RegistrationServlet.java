/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.controllers;

import ch.heigvd.amt.gary.services.dao.AccountDAO;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author franz
 */
public class RegistrationServlet extends HttpServlet
{
   @EJB
   AccountDAO dao;
   
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("pageTitle", "Registration");
        request.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(request, response);
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");
        String action = request.getParameter("action"); 
        
        
        
        if(action.equals("register")) {
            
            // dao.exists(afsfdsa)
            
            if(!password.equals(passwordConfirmation)) {
                // Registration failure
                request.setAttribute("Error", "Registration Failure");
                request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
            } else {
                
            }
            
           
        }
        
    }

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
            throws ServletException, IOException
    {
        processRequest(request, response);
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
            throws ServletException, IOException
    {
       String mail = request.getParameter("mail");
       String pass1 = request.getParameter("pass1");
       String pass2 = request.getParameter("pass2");
       
       dao.create(mail, "Michel", "Stephan", pass1);
       
       processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
