package moe.neptunenoire.mushi.maikissreo.cotllor;

import java.util.LinkedList;

import moe.neptunenoire.mushi.maikissreo.utils;
import moe.neptunenoire.mushi.maikissreo.inter.DownLink;

/**
 * 任务分配器
 */
public class DownLinkKubaru extends utils implements DownLink {
    /** 储存任务队列 */
    private LinkedList<Object> datalist = new LinkedList<>();
    /**
     * 进行访问将会分配一个不重复的任务
     */
    @Override
    public Object getData(){
        synchronized(datalist){
            if (getSize() > 0) {
                Object data = datalist.getFirst();
                datalist.removeFirst();
                return data;
            }else{
                return null;
            }
        }
    }
    /**
     * 返回任务队列的数量
     */
    @Override
    public int getSize(){
        return datalist.size();
    }
    /**
     * 添加任务到队列
     */
	@Override
	public void updataInfo(Object obj) {
        synchronized(datalist){
            if (obj instanceof String[]) {
                for (String var : (String[])obj) {
                    datalist.add(var);
                }
            }else{
                datalist.add(obj);
            }
        }
	}
}