package moe.neptunenoire.mushi.inter;

/**
 * 任务分配器接口
 * 
 * 将接受一条任务，
 * 并将任务添加至任务队列中等待读取
 */
public interface DownLink {
    /**
     * 从保存的任务队列中取得一条任务
     */
    public Object getData();
    /**
     * 获得队列中任务的总长度
     */
    public int getSize();
    /**
     * 向任务队列中添加一条数据
     */
    public void updataInfo(Object obj);
}