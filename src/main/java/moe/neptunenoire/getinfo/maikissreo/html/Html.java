package moe.neptunenoire.getinfo.maikissreo.html;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import moe.neptunenoire.getinfo.maikissreo.utils;
import moe.neptunenoire.getinfo.maikissreo.inter.DownLink;
import moe.neptunenoire.getinfo.maikissreo.inter.HtmlLinkFinder;
import moe.neptunenoire.getinfo.maikissreo.inter.Htmls;

/**
 * 负责对Html进行解析的类
 */
public class Html extends utils implements HtmlLinkFinder {
    private DownLink down = null;
    /** 初始化自定义html过滤规则 */
    private Htmls htmls = null;
    /** 保存解析URL */
    private String htmlUrl = null;
    /**
     * 此初始化用于加载自定义html解析规则
     */
    public Html(Htmls htmls, DownLink down, String url){
        if (htmls != null && isNull(url) == false && down != null) {
            this.htmls = htmls;
            this.htmlUrl = url;
            Add(down);
            sendInfo(null);
        }else{
            throw new NullPointerException("不能为空");
        }
    }
    public Html(String url, DownLink down){
        this.down = down;
        this.htmlUrl = url;
        Kai();
    }
    private void Kai(){
        try {
            Document doc = Jsoup.connect(htmlUrl).get();
            String title = doc.title();
            sendInfo(title);
		} catch (IOException e) {}
    }
    /**
     * 以下是实现接口
     * 添加和删除连接分配器。
     * 目的是将解析出来的数据送到数据分配器。
     * 等待处理
     */
    /**
     * 添加任务分配器
     */
	@Override
	public void Add(DownLink link) {
        if (link != null) {
            this.down = link;
        }
	}
    /**
     * 移除当前任务分配器
     */
	@Override
	public void remove(DownLink link) {
		this.down = null;
	}
    /**
     * 将数据发送到分配器
     */
	@Override
	public boolean sendInfo(Object info) {
        if (htmls != null) {
            down.updataInfo(htmls.Html(htmlUrl));
        }else{
            if (info == null) {
                throw new NullPointerException("Object为空");
            }else{
                down.updataInfo(info);
            }
        }
		return true;
	}
}