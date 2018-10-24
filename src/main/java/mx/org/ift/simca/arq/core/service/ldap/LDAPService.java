package mx.org.ift.simca.arq.core.service.ldap;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author KODE
 */
public class LDAPService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LDAPService.class);
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static HashMap<String, Object> consultaUsrActiveDirectory(String user, String pass) {
        HashMap<String, Object> lista = new HashMap<String, Object>();
        String token = getTokenAcceso();

        if (token != null) {
            LDAPLogin ldapLogin = new LDAPLogin(user, pass, token);
            if (getAcceso(ldapLogin).equals("Success")) {
                lista.put("autentificado", true);
            } else {
                lista.put("autentificado", false);
            }
        } else {
            lista.put("autentificado", false);
        }

        return lista;
    }

    private static String getTokenAcceso() {
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        ResultToken resultToken = null;
        String result = null;

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "Content-Type=application/json&grant_type=client_credentials");
        Request request = new Request.Builder()
                .url("http://172.17.42.71:8280/token")
                .post(body)
                .addHeader("authorization", "Basic aWh2Xzd0al9odWkwb3VJSDFVUXpHRVdMYzFrYTozV3Rmak1aaTRlZDJMWFRaNFA1NnFXb09ETzRh")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response != null) {
                if (response.body() != null) {
                    String resultTokenJson = response.body().string();

                    if (response.isSuccessful()) {
                        resultToken = gson.fromJson(resultTokenJson, ResultToken.class);
                        result = resultToken.getAccess_token();
                    } else {
                        LOGGER.error("Error al solicitar Token: " + response.code() + "-" + response.message());
                    }
                }
            }
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }

        return result;
    }

    private static String getAcceso(LDAPLogin login) {
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        String result = "Fail";

        RequestBody body = RequestBody.create(JSON, gson.toJson(login));
        Request request = new Request.Builder()
                .url("http://172.17.42.71:8280/LDAP_LOGIN/VALIDAR_USUARIO")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer " + login.getToken())
                .build();
        try {
            Response response = client.newCall(request).execute();

            if (response != null) {
                String resultJson = response.body().string();

                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        ResultLDAPService resultLDAPService = gson.fromJson(resultJson, ResultLDAPService.class);
                        result = resultLDAPService.getResult().getMessage();
                    } else {
                        LOGGER.error("Error credenciales de usuario: "+response.code() + "-" + response.message());
                    }
                }
            }
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }

        return result;
    }

}
