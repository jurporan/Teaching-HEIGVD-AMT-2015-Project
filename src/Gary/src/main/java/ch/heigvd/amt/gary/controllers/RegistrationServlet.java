/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.controllers;

import ch.heigvd.amt.gary.services.LoginServiceLocal;
import ch.heigvd.amt.gary.services.dao.AccountDAO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
   
   @EJB
   LoginServiceLocal loginService;
   
   public static final String REGISTRATION_VIEW = "/WEB-INF/views/registration.jsp";
   public static final String ATT_ERRORS = "errors";
   public static final String ATT_RESULT = "result";
   public static final String FIELD_EMAIL = "email";
   public static final String FIELD_PWD = "password";
   public static final String FIELD_NAMES = "names";
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
        // Display registration page
        this.getServletContext().getRequestDispatcher(REGISTRATION_VIEW)
                                .forward(request, response);
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
        String result;
        Map<String, String> errors = new HashMap<>();
        
        // Processing form data
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");
        
        try {
            firstnameAndLastnameValidation(firstname, lastname);        
        } catch (Exception e) {
            errors.put(FIELD_NAMES, e.getMessage());
        }
        try {
            emailValidation(email);         
        } catch (Exception e) {
            errors.put(FIELD_EMAIL, e.getMessage());
        }
        try {
            passwordValidation(password, passwordConfirmation);         
        } catch (Exception e) {
            errors.put(FIELD_PWD, e.getMessage());
        }
        
        if(errors.isEmpty()) {
            dao.create(email, firstname, lastname, password);
            result = "Succès de l'inscription.";
        } else {
            result = "Echec de l'inscription.";
        }
   
        request.setAttribute(ATT_ERRORS, errors);
        request.setAttribute(ATT_RESULT, result);
        
        this.getServletContext().getRequestDispatcher(REGISTRATION_VIEW)
                                .forward(request, response);    
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
    
    
    private void firstnameAndLastnameValidation (String firstname, String lastname) throws Exception {
        if (firstname != null && lastname != null) {
            if (firstname.trim().length() < 3 || lastname.trim().length() < 3) {
                throw  new Exception("Le nom et le prénom doivent posséder plus"
                        + "de 3 caractères.");
            }          
        } else {
            throw new Exception("Veuillez saisir votre nom et prénom.");
        }

    }

    private void emailValidation(String email) throws Exception {
        if(email == null || (email.trim().length() == 0)) {
            throw new Exception("L'e-mail ne doit pas être vide.");
        }
        if(!isValidEmailAddress(email)) {
            throw new Exception("Veuillez saisir une adresse e-mail valide.");
        }
        
        if (dao.exists(email)) {
            throw new Exception("Un utilisateur possédant le même e-mail existe déjà.");
        }
    }
    
    private boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
    
    private void passwordValidation(String password, String passwordConfirmation) throws Exception {
        if(password != null || password.trim().length() != 0 
                            || passwordConfirmation != null 
                            || passwordConfirmation.trim().length() != 0) {
            if(!password.equals(passwordConfirmation)) {
                throw new Exception("Les mots de passe ne correspondent pas.");
            }
            if(password.trim().length() < 8) {
                throw new Exception("Le mot de passe doit contenir au moins 8 caractères.");
            }       
        } else {
            throw new Exception("Veuillez saisir un mot de passe.");
        }
        
    }
    
}
