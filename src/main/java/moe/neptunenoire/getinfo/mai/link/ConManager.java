package moe.neptunenoire.getinfo.mai.link;

import moe.neptunenoire.getinfo.mai.getpic.X;

public class ConManager {

    public static void manager(String firsturl){

        int n = 1;
        int i;
        Connector connector;
        while (n <= 90){
            connector = new Connector();
            firsturl = connector.sentRequest(firsturl);
            i = X.n;
            if (i > n)
                n++;
            else
                break;
        }
    }
}
