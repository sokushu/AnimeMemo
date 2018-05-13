package moe.neptunenoire.getinfo.maikissreo.cotllor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import moe.neptunenoire.getinfo.maikissreo.down.Download;
import moe.neptunenoire.getinfo.maikissreo.html.Html;
import moe.neptunenoire.getinfo.maikissreo.inter.Htmls;

/**
 * 控制器，
 * 用于分配下载任务，页面解析任务
 * 控制运作
 */
public final class Controller {
    /** 连接追踪的层数 */
    private int Linknum = 0;
    /** 启动时的初始连接 */
    private String startUrl = null;
    /** 是否启用多线程 */
    private boolean thread = true;
    /** 
     * 最大限制下载进程数
     * 针对某些网站的策略，例如不能同时下载超过4个图片的网站
     */
    private int threadNum = 4;
    /** 
     * 设置连接解析，下载的限制数目，到这个数目则会停止运行
     * 例如100个下载链接，如果设置90则会下载其中的90个数据
     * 如果设为0则为无限制
     */
    private int LinkDownNum = 0;
    /** 下载文件的保存目录 */
    private String savePath = null;
    /** 下载文件的保存文件名 */
    private String fileName = null;
    /** 下载文件目录中如果有同名文件是否覆盖 */
    private boolean isCover = false;
    /** 
     * 使用自定义的规则
     * 如果设置自定义规则的Html解析器，则会使用
     */
    private Htmls htmls = null;
    /**
     *  是否启用下载
     *  如果启用下载，则会将数据下载
     *  不启用则会将匹配的数据写入文本文件
     */
    private boolean isDownLoad = true;
    /** 
     * 是否将信息保存进MySQL数据库
     * 适合字段，文本的保存
     */
    private boolean saveAsMySQL = false;


    private Controller(){}
    /** 创建一个新的实例 */
    public static Controller create(){
        return new Controller();
    }
    /** 开始运行 */
    public void Run(){
        if (thread) {
            ExecutorService threadPool = Executors.newCachedThreadPool();
        
            Download down = new Download(savePath, fileName, isCover);
            DownLinkKubaru downLinkKubaru = new DownLinkKubaru();
            /** 开始解析 */
            for (int i = 0; i < 4; i++) {
                threadPool.execute(new Runnable(){
                    @Override
                    public void run() {
                        if (htmls != null) {
                            new Html(htmls, downLinkKubaru, startUrl);
                        }else{
                            new Html(startUrl, downLinkKubaru);
                        }
                    }
                });
            }
            if (isDownLoad) {
                /** 
                 * 循环等待解析连接
                 * 如果分配器中没有任何数据，就一直等待直到有数据
                 */
                while (true) {
                    if (downLinkKubaru.getSize() > 0) {
                        ExecutorService downThreadPool = new ThreadPoolExecutor(4, threadNum, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
                        /** 开始下载 */
                        for (int i = downLinkKubaru.getSize(); i > 0; i--) {
                            downThreadPool.execute(new Runnable(){
                                @Override
                                public void run() {
                                    down.down(downLinkKubaru.getData().toString());
                                }
                            });
                        }
                        downThreadPool.shutdown();
                        break;
                    }
                }
            }else{

            }
        }else{
            /** 初始化下载器 */
            Download down = new Download(savePath, fileName, isCover);
            /** 初始化分配器 */
            DownLinkKubaru downLinkKubaru = new DownLinkKubaru();
            /** 
             * 判断是否设置自定义html解析器
             * 来决定初始化Html解析器的方式
             */
            if (htmls != null) {
                new Html(htmls, downLinkKubaru, startUrl);
            }else{
                new Html(startUrl, downLinkKubaru);
            }
            /** 开始下载 */
            while (downLinkKubaru.getSize() > 0) {
                down.down(downLinkKubaru.getData().toString());
            }
        }
    }

    /**
     * 设置连接追踪的层数
     * @param linknum 设置连接追踪层数
     */
    public Controller setLinknum(int linknum) {
        if (linknum > 0) {
            Linknum = linknum;
        }
        return this;
    }
    /**
     * 设置下载的限制数目
     * @param linkDownNum the linkDownNum to set
     */
    public Controller setLinkDownNum(int linkDownNum) {
        if (linkDownNum > 0) {
            LinkDownNum = linkDownNum;
        }
        return this;
    }
    /**
     * 设置开始下载的连接
     * @param startUrl the startUrl to set
     */
    public Controller setStartUrl(String startUrl) {
        this.startUrl = startUrl;
        return this;
    }
    /**
     * 是否启用多线程
     * @param thread 最大20
     */
    public Controller setThread(Boolean thread) {
        this.thread = thread;
        return this;
    }
    /**
     * @param fileName the fileName to set
     */
    public Controller setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
    /**
     * @param isCover the isCover to set
     */
    public Controller setIsCover(boolean isCover) {
        this.isCover = isCover;
        return this;
    }
    /**
     * @param savePath the savePath to set
     */
    public Controller setSavePath(String savePath) {
        this.savePath = savePath;
        return this;
    }
    /**
     * @param threadNum the threadNum to set
     */
    public Controller setThreadNum(int threadNum) {
        this.threadNum = threadNum;
        return this;
    }
    /**
     * @param htmls the htmls to set
     */
    public Controller setHtmls(Htmls htmls) {
        this.htmls = htmls;
        return this;
    }
    /**
     * @param saveAsMySQL the saveAsMySQL to set
     */
    public Controller setSaveAsMySQL(boolean saveAsMySQL) {
        this.saveAsMySQL = saveAsMySQL;
        return this;
    }
}