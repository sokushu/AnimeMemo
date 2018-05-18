package moe.neptunenoire.mushi.mai.link;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;

public class ToGet {

    public static HttpsURLConnection sentGET(String urlstr){

        try
        {
            if ((urlstr != null) && !urlstr.equals("")){
                URL url = new URL(urlstr);
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                con.setConnectTimeout(20000);
                con.setReadTimeout(20000);
                con.setUseCaches(false);
                con.setInstanceFollowRedirects(false);

                con.setRequestMethod("GET");
                con.connect();

                return con;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
