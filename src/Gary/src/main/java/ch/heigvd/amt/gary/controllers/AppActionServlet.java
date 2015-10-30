/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.controllers;

import ch.heigvd.amt.gary.services.AppsManagerLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Miguel
 */
public class AppActionServlet extends HttpServlet {

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
         String action = request.getParameter("action") == null ? 
                                       "null" : request.getParameter("action");
         String formAction = request.getParameter("formAction") == null ? 
                                       "null" : request.getParameter("formAction");
         switch (action) {
            case "add":
               if (formAction.equals("create"))
               {
                  String name = request.getParameter("name");
                  
                  if (name.isEmpty())
                  {
                     request.setAttribute("error", "You must fill the name field.");
                  }
                  else
                  {
                     appsManager.createApp(name, request.getParameter("description"),
                                           UUID.randomUUID().toString(), 0, 
                                           request.getParameter("state").equals("Enabled"), 
                                             (long)request.getSession().getAttribute("id"));
                     response.sendRedirect("appslist");
                     break;
                  }
               }
               
               request.setAttribute("pageTitle", "Register new app");
               request.getRequestDispatcher("WEB-INF/views/registerapp.jsp").forward(request, response);
               break;
            case "edit":
               if (formAction.equals("edit"))
               {
                  String name = request.getParameter("name");
                  
                  if (name.isEmpty())
                  {
                     request.setAttribute("error", "You must fill the name field.");
                  }
                  else
                  {
                     appsManager.editApp(Long.valueOf(request.getParameter("app")), 
                                         request.getParameter("name"), 
                                         request.getParameter("description"), 
                                         request.getParameter("state").equals("Enabled"));
                     response.sendRedirect("appslist");
                     break;
                  }
               }
               else if (request.getParameter("app") == null || request.getParameter("app").equals(""))
               {
                  response.sendRedirect("appslist");
                  break;
               }
               
               Object app = appsManager.getApp(Long.valueOf(request.getParameter("app")));
               request.setAttribute("app", app);
               
               request.setAttribute("pageTitle", "App details");
               request.setAttribute("email", request.getSession().getAttribute("email"));
               request.getRequestDispatcher("WEB-INF/views/editapp.jsp").forward(request, response);
               break;
            default:
               response.sendRedirect("appslist");
               break;
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
