/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viaCep;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author User
 */
public class ClienteViaCepWS {
    //Método que puxa os dados do viacep pelo cep
    public static String buscaCep(String cep){
        String json;
        
        try{
            URL url = new URL("http://viacep.com.br/ws/"+cep+"/json/");
            URLConnection urlConn = url.openConnection();
            InputStream is = urlConn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
            
            StringBuilder jsonSb = new StringBuilder();
            
            br.lines().forEach(l -> jsonSb.append(l.trim()));
            
            json = jsonSb.toString();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return json;
    }
    
    //Método do logradouro
    public static String[] logCep(String json) throws JSONException{
        JSONObject js = new JSONObject(json);
        String[] logCep = new String[3];
        logCep[0] = js.getString("logradouro");
        logCep[1] = js.getString("bairro");
        logCep[2] = js.getString("uf");        
        return logCep;      
    }
    
}
