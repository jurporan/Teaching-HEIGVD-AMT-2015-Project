/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services;

import ch.heigvd.amt.gary.models.entities.Account;
import ch.heigvd.amt.gary.services.dao.AccountDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author franz
 */
@Stateless
public class LoginService implements LoginServiceLocal
{
    @EJB
    AccountDAO account;

    @Override
    public long countTotalAccounts()
    {
       return account.count();
    }
    
    @Override
    public Account verifyLogin(String login, String password)
    {
        return account.login(login, password);     
    }
    
    @Override
    public void passwordValidation(String password, String passwordConfirmation) throws Exception 
    {
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
    
    @Override
    public void firstnameAndLastnameValidation (String firstname, String lastname) throws Exception {
        if (firstname != null && lastname != null) {
            if (firstname.trim().length() < 3 || lastname.trim().length() < 3) {
                throw  new Exception("Le nom et le prénom doivent posséder plus"
                        + "de 3 caractères.");
            }          
        } else {
            throw new Exception("Veuillez saisir votre nom et prénom.");
        }

    }

    @Override
    public void emailValidation(String email) throws Exception {
        if(email == null || (email.trim().length() == 0)) {
            throw new Exception("L'e-mail ne doit pas être vide.");
        }
        if(!isValidEmailAddress(email)) {
            throw new Exception("Veuillez saisir une adresse e-mail valide.");
        }
        
        if (account.exists(email)) {
            throw new Exception("Un utilisateur possédant le même e-mail existe déjà.");
        }
    }
    
    @Override
    public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
 
    @Override
    public void editAccount(long id, String firstname, String lastname, String password)
    {
        Account a = account.get(id);
        a.setFirstName(firstname);
        a.setLastName(lastname);
        a.setPassword(password);
    }
}
