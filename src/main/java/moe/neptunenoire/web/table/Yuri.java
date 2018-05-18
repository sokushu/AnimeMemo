package moe.neptunenoire.web.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Yuri
 */
@Entity
@Table(name="yuri")
public class Yuri {

    /** 权限ID */
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Integer id;

    /** 权限名称 */
    @Column(name="name")
    private String name;

    /** 权限的相关信息 */
    @Column(name="info")
    private String info;


    public Yuri(){

    }
    public Yuri(Integer id, String name, String info){
        super();
        this.id = id;
        this.name = name;
        this.info = info;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

}