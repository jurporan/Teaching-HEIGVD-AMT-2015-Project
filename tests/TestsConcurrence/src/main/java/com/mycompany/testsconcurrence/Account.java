/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testsconcurrence;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class Account implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String mail;
    private String firstName;
    private String lastName;
    private String password;

    private List<App> createdApps = new LinkedList<App>();

    /**
    * Creates a new empty Account
    */
    public Account() {}

    /**
    * Creates a new Account
    *
    * @param mail email address
    * @param firstName first name of the user
    * @param lastName last name of the user
    * @param password password used for login
    */
    public Account(String mail, String firstName, String lastName, String password)
    {
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account))
        {
            return false;
        }

        Account other = (Account) object;

        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return "ch.heigvd.amt.gary.models.entities.Account[ id=" + id + " ]";
    }

    /**
    * Add a new application in the account's list of created apps
    *
    * @param app application to add in the list
    */
    public void addApp (App app)
    {
        createdApps.add(app);
    }

    // Getters and Setters

    /**
    * Get the ID of the account
    *
    * @return the ID of the account
    */
    public Long getId()
    {
        return id;
    }

    /**
    * Set the ID of the account, should be set automatically by the data store
    *
    * @param id the ID to set
    */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
    * Get the list of created apps
    *
    * @return the list of created apps
    */
    public List getApps()
    {
        return createdApps;
    }

    /**
    * Get the account's email address
    *
    * @return the list of created apps
    */
    public String getMail()
    {
        return mail;
    }

    /**
    * Set the account's email address
    *
    * @param mail the email address to set
    */
    public void setMail(String mail)
    {
        this.mail = mail;
    }

    /**
    * Get the first name
    *
    * @return the first name
    */
    public String getFirstName()
    {
        return firstName;
    }

    /**
    * Set the first name
    *
    * @param firstName the first name to set
    */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
    * Get the last name
    *
    * @return the last name
    */
    public String getLastName()
    {
        return lastName;
    }

    /**
    * Set the last name
    *
    * @param lastName the first name to set
    */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
    * Get the password
    *
    * @return the password
    */
    public String getPassword()
    {
        return password;
    }

    /**
    * Set the password
    *
    * @param password the new password to set
    */
    public void setPassword(String password)
    {
        this.password = password;
    }
}
