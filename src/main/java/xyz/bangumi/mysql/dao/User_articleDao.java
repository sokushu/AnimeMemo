package xyz.bangumi.mysql.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * User_articleDao
 */
//@Mapper
public interface User_articleDao {
    /**
     * 写入记事本
     */
    public void addjishi();
    /**
     * 读取记事本
     */
    public String loadjishi();
}