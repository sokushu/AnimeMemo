package moe.neptunenoire.web.table;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Yuri_Users
 */
@Entity
@Table(name="yuri_users")
public class Yuri_Users {

    /** 项目ID */
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Integer id;

    /** 权限ID */
    @Column(name="yuri_id")
    private Integer yuri_id;

    /** 用户ID */
    @Column(name="uid")
    private Long uid;

    /** 创建时间 */
    @Column(name="create_time")
    private Date create_time;

    public Yuri_Users(){

    }
    public Yuri_Users(Integer id, Integer yuri_id, Long uid, Date create_time){
        super();
        this.id = id;
        this.yuri_id = yuri_id;
        this.uid = uid;
        this.create_time = create_time;
    }
    /**
     * @param create_time the create_time to set
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @param uid the uid to set
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }
    /**
     * @param yuri_id the yuri_id to set
     */
    public void setYuri_id(Integer yuri_id) {
        this.yuri_id = yuri_id;
    }
    /**
     * @return the create_time
     */
    public Date getCreate_time() {
        return create_time;
    }
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @return the uid
     */
    public Long getUid() {
        return uid;
    }
    /**
     * @return the yuri_id
     */
    public Integer getYuri_id() {
        return yuri_id;
    }
}