/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.ift.simca.arq.core.service.ldap;

/**
 *
 * @author 
 */
public class LDAPLogin {
    private String user;
    private String pass;
    private String token;

    public LDAPLogin(String user, String pass, String token) {
        this.user = user;
        this.pass = pass;
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
    
}
