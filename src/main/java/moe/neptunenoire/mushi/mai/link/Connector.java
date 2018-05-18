package moe.neptunenoire.mushi.mai.link;

import moe.neptunenoire.mushi.mai.filter.HTMLs;
import moe.neptunenoire.mushi.mai.getpic.Downloader;
import moe.neptunenoire.mushi.mai.getpic.X;

public class Connector {


    public String sentRequest(String urlstr){

        SentHttpsRequest sent = new SentHttpsRequest();
        String content = sent.sentRequest(urlstr);

        String nextpage = HTMLs.catch_nextpage(HTMLs.catch_div(content));

        String picurl = HTMLs.catch_pic(HTMLs.catch_div(content));
        Downloader downloader = new Downloader();
        downloader.setPicurl(picurl);
        downloader.start();

        try
        {
            downloader.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        X.n = Integer.parseInt(HTMLs.catch_num(nextpage));
        return nextpage;
    }
}
