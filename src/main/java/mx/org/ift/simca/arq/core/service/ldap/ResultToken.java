/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.ift.simca.arq.core.service.ldap;

/**
 *
 * @author KODE
 */
public class ResultToken {

    private String access_token;
    private String scope;
    private String token_type;
    private String expires_in;
    private String error_description;
    private String error;

    /**
     * Get the value of error_description
     *
     * @return the value of error_description
     */
    public String getError_description() {
        return error_description;
    }

    /**
     * Set the value of error_description
     *
     * @param error_description new value of error_description
     */
    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    /**
     * Get the value of error
     *
     * @return the value of error
     */
    public String getError() {
        return error;
    }

    /**
     * Set the value of error
     *
     * @param error new value of error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Get the value of expires_in
     *
     * @return the value of expires_in
     */
    public String getExpires_in() {
        return expires_in;
    }

    /**
     * Set the value of expires_in
     *
     * @param expires_in new value of expires_in
     */
    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    /**
     * Get the value of token_type
     *
     * @return the value of token_type
     */
    public String getToken_type() {
        return token_type;
    }

    /**
     * Set the value of token_type
     *
     * @param token_type new value of token_type
     */
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    /**
     * Get the value of scope
     *
     * @return the value of scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * Set the value of scope
     *
     * @param scope new value of scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * Get the value of access_token
     *
     * @return the value of access_token
     */
    public String getAccess_token() {
        return access_token;
    }

    /**
     * Set the value of access_token
     *
     * @param access_token new value of access_token
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

}
