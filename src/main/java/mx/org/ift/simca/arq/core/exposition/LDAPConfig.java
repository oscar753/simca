/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.ift.simca.arq.core.exposition;

/**
 *
 * @author KODE
 */
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class LDAPConfig {

    public static HashMap<String, Object> consultaUsrActiveDirectory(String user, String password) {
//		System.out.println("password-->"+password);
        HashMap<String, Object> lista = new HashMap<String, Object>();

        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.PROVIDER_URL, "ldap://10.34.144.4:389");
        env.put(Context.SECURITY_PRINCIPAL, user + "@ift.org.mx");
        env.put(Context.SECURITY_CREDENTIALS, password);
        env.put(Context.REFERRAL, "follow");

        LdapContext ctx = null;

        try {
            ctx = new InitialLdapContext(env, null);// Authenticate the logon user
            lista.put("autentificado", true);

            String searchBase = "DC=ift,DC=org,DC=mx";
            String searchFilter = "(sAMAccountName=" + user + ")";
            SearchControls sCtrl = new SearchControls();
            sCtrl.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String returnAttrs[] = {"name"};
            sCtrl.setReturningAttributes(returnAttrs);

            NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter, sCtrl);

            
            while (answer.hasMoreElements())
            {
                
                SearchResult sr= (SearchResult)answer.next();
                formatAttributes(sr.getAttributes(),lista);
                
            }

        } catch (NamingException ex ) {
            System.err.println("No hay valor para consultar: " + ex);
            lista.put("autentificado", false);

        } catch (Exception ex) {
            System.err.println("No hay valor para consultar: " + ex);
            lista.put("autentificado", false);
        }

        return lista;
    }
    
    
    private static Map formatAttributes(Attributes attrs,HashMap<String,Object> columns) throws Exception
    {
        String value= null;
	if (attrs== null)
        {
            System.out.println();
        }
        else
        {
            try
            {
                for (NamingEnumeration enumeration = attrs.getAll(); enumeration.hasMore();)
                {
                    Attribute attrib = (Attribute)enumeration.next();
                    for (NamingEnumeration e= attrib.getAll(); e.hasMore();)
                    {
                        value = e.next().toString();
                        columns.put(attrib.getID(),value);
                    }
                }
            } 
            catch (NamingException e)
            {
                return null;
            }
        }
        return columns;
      }

}
