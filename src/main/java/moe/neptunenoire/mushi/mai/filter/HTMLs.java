package moe.neptunenoire.mushi.mai.filter;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    专门处理请求返回的网页
 */
public class HTMLs {

    public static void saveToFile(HttpURLConnection con){

        if(null != con){
            try
            {
                InputStream is = con.getInputStream();
                OutputStream os = new FileOutputStream("test.html");
                int len;
                byte[] buffer = new byte[4096];
                while ((len = is.read(buffer)) != -1){
                    os.write(buffer, 0, len);
                }

            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("save to file fail, con = null");
        }
    }

    public static void saveToFile_2(String str){

        try
        {
            File file = new File("test2.html");
            PrintWriter pw = new PrintWriter(file);
            pw.print(str);
            pw.flush();
            pw.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static String saveToString(HttpURLConnection con){

        if(null != con) {

            int len;
            byte[] buffer = new byte[4096];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                InputStream is = con.getInputStream();
                while((len = is.read(buffer)) != -1){
                    bos.write(buffer, 0, len);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println("begin:" + bos.toString());
            return bos.toString();
        }
        System.out.println("save to string fail, con = null");
        return null;
    }

    public static String readFile() {

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("test2.html"));
            StringBuilder result=new StringBuilder();
            String line=null;
            while(( line = reader.readLine()) != null){
                result.append(line);
            }

            return result.toString();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String catch_div(String str1){

        //System.out.println(str1);
        if (str1 == null || str1.equals("")){
            System.out.println("str1 = null");
        }
        else {
            String regex = "(?<=<div id=\"i3\">)[a-z0-9A-Z_.,/:;?&\\ \t\n\r\"'()<>=()-]*?(?=</div>)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str1);
            while (matcher.find()){
               // System.out.println(matcher.group(0));
                //System.out.println("catch_div over");
                return matcher.group(0);
            }
        }
        System.out.println("catch_div over");
        return null;
    }

    public static String catch_pic(String str2){

        if (str2 == null || str2.equals("")){
            System.out.println("str2 = null");
        }
        else{
            String regex = "(?<=src=\")[a-z0-9A-Z_.,/:;?&\\ \t\n\r\"'()<>=-]*?(?=\")";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str2);
            while (matcher.find()){
                //System.out.println(matcher.group(0));
                //System.out.println("catchpic over");
                return matcher.group(0);
            }
        }
        System.out.println("catchpic over");
        return null;
    }

    public static String catch_nextpage(String str){

        if (str == null || str.equals("")){
            System.out.println("str = null");
        }
        else{
            String regex = "(?<=href=\")[a-z0-9A-Z_.,/:;?&\\ \t\n\r\"'()<>=-]*?(?=\")";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()){
                System.out.println(matcher.group(0));
                //System.out.println("catch_nextpage over");
                return matcher.group(0);
            }
        }
        System.out.println("catch_nextpage over");
        return null;
    }

    public static String catch_num(String nextpage){

        if (nextpage == null || nextpage.equals("")){
            System.out.println("nextpage = null");
        }
        else{
            //System.out.println("nextpage = "+nextpage);
            String regex = "\\d{1,3}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(nextpage);
            while (matcher.find()){
                System.out.println(matcher.group(0));
                //System.out.println("catch_nextpage over");
                return matcher.group(0);
            }
        }
        System.out.println("catch_num over");
        return null;
    }

}
