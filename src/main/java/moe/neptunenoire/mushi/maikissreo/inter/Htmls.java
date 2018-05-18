package moe.neptunenoire.mushi.maikissreo.inter;

/**
 * 实现这个接口，即可自定义解析规则
 */
public interface Htmls {
    /**
     * 将解析的数据以集合的形式返回
     * 
     * 可以返回多条数据
     */
    public String[] Html(String url);
}