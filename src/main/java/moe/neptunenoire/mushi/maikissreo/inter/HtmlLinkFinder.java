package moe.neptunenoire.mushi.maikissreo.inter;

/**
 * HTML解析器
 * 
 * 用于将从Html中解析出来的数据存入任务分配器中
 */
public interface HtmlLinkFinder {
    /**
     * 添加一个任务分配器
     */
    public void Add(DownLink link);
    /**
     * 将当前分配器移除
     */
    public void remove(DownLink link);
    /**
     * 向分配器传送一条解析数据
     */
    public boolean sendInfo(Object info); 
}