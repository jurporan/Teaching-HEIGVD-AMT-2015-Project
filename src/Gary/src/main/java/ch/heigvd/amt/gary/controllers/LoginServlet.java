/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.controllers;

import ch.heigvd.amt.gary.models.entities.Account;
import ch.heigvd.amt.gary.services.LoginServiceLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jan Purro
 * 
 * Called when the "login" button is clicked. 
 * Delegate the the verification of the login informations to a service and
 * transmit the request either to the login.jsp with an error attribute if the 
 * login was incorrect or to the appslis.jsp if login was ok.
 * 
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet
{
    @EJB
    LoginServiceLocal loginService;
    
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
    {}
    
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
         String action = request.getParameter("action") == null ? 
                                       "null" : request.getParameter("action");
        
         // If the user wants to logout, invalidate his session and 
         // redirect him to the welcome page
         if(action.equals("logout"))
         {
            request.getSession().invalidate();
            response.sendRedirect("welcome");   
         }
         // If the user wants to login, redirect him to the login page
         else
         {
            request.setAttribute("pageTitle", "Login");
            this.getServletContext().getRequestDispatcher( "/WEB-INF/views/login.jsp" ).forward( request, response );
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
            throws ServletException, IOException
    {
        // Get the parameters sent by the user
        String result;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String action = request.getParameter("action"); 
        
        if(action.equals("login"))
        {
            // Verify the couple email / password
            Account account = loginService.verifyLogin(email, password);
            
            // Verify if the account exists
            if(account != null)
            {
                // Get his email and session ID
               request.getSession().setAttribute("email", email);
               request.getSession().setAttribute("id", account.getId());
               response.sendRedirect("appslist");
            } 
            else 
            {
                // The user cannot login
                 result = "Echec de l'authentification.";
                 request.setAttribute("result", result);
                 this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp")
                                         .forward(request, response);
            }
        }             
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
