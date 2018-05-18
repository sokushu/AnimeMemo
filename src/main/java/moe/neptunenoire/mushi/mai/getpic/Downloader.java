package moe.neptunenoire.mushi.mai.getpic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader extends Thread {

    private String picurl;
    private String numstr;

    public void setPicurl(String picurl){
        this.picurl = picurl;
        this.numstr = numstr;
    }

    @Override
    public void run() {
        download_pic();
    }


    public void download_pic(){

        try
        {
            URL url = new URL(picurl);
            //System.out.println("here is ok");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setConnectTimeout(20000);
            con.setReadTimeout(20000);
            con.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36");
            con.setRequestMethod("GET");
            con.connect();

            int responseCode;
            if ((responseCode = con.getResponseCode()) != 200){
                System.out.println("responseCode = " + responseCode);
                return;
            }

            InputStream is = con.getInputStream();
            OutputStream os = new FileOutputStream(X.n + ".jpg");
            X.n++;
            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer)) != -1){
                os.write(buffer, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
