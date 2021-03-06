package App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnexionHTTP {

    private final String UrlQCM;

    public ConnexionHTTP(String url) {

        this.UrlQCM = url;
    }


    public String getRequest() {
        try {
            URL url = new URL(UrlQCM);
            HttpURLConnection  urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader( new InputStreamReader( urlConnection.getInputStream() )  );
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "pb url";
        } catch(IOException e){
            e.printStackTrace();
            return "pb d'acces au serveur";
        }
        return "";
    }

}
