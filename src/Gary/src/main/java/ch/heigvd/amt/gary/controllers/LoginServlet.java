/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.controllers;

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
    {
    //    response.setContentType("text/html;charset=UTF-8");
   //     String email = request.getParameter("email");
   //     String password = request.getParameter("password");
          String action = request.getParameter("action"); 
   //     request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
 /*   
        if(action.equals("login"))
        {
            if(loginService.verifyLogin(email, password) != null)
            {
                request.getSession().setAttribute("email", email);
                response.sendRedirect("/appslist");
            }
            else
            {
                request.setAttribute("Error", "Authentification Failure");
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            }
        }
        
        else if (action.equals("logout"))
        {
            request.getSession().invalidate();
            response.sendRedirect("/welcome");
        }
        
       */

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
        this.getServletContext().getRequestDispatcher( "/WEB-INF/views/login.jsp" ).forward( request, response );
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
    //   processRequest(request, response);
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
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
