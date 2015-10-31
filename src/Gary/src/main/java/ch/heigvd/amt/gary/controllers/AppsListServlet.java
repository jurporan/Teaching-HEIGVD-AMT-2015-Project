/*
 * The servlet that manage and show the list of applications.
 * Created on 01.10.2015 by Miguel Santamaria
 */
package ch.heigvd.amt.gary.controllers;

import ch.heigvd.amt.gary.services.AppsManagerLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Miguel
 */
@WebServlet(name = "ServletAppsList", urlPatterns = {"/appslist"})
public class AppsListServlet extends HttpServlet {

   @EJB
   AppsManagerLocal appsManager;
   
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
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
         int pageNumber = request.getParameter("page") == null ? 
                                       1 : Integer.parseInt(request.getParameter("page"));
         int itemsPerPage = request.getParameter("per_page") == null ? 
                                       10 : Integer.parseInt(request.getParameter("per_page"));
         int numberOfPages = (int)Math.ceil(appsManager.countForAccount((long)request.getSession().getAttribute("id")) / (double)itemsPerPage);
         
         Object apps = appsManager.getUserApps((long)request.getSession().getAttribute("id"), 
                                               pageNumber, 
                                               itemsPerPage);
         
         request.setAttribute("pageTitle", "Your apps");
         request.setAttribute("apps", apps);
         request.setAttribute("email", request.getSession().getAttribute("email"));
         request.setAttribute("pageNumber", pageNumber);
         request.setAttribute("itemsPerPage", itemsPerPage);
         request.setAttribute("numberOfPages", numberOfPages);
         request.getRequestDispatcher("WEB-INF/views/appslist.jsp").forward(request, response);
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
           throws ServletException, IOException {
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
           throws ServletException, IOException {
      processRequest(request, response);
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
