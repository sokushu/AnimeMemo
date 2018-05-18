package moe.neptunenoire.mysql.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 短网址服务
 */
@Entity
@Table(name="shoturl", indexes={@Index(name="key_ofshoturl", columnList="id")})
public class ShotUrl {
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Integer id;
    @Column(name="url", nullable=false)
    private String url;
    

    public ShotUrl(){

    }
    public ShotUrl(Integer id, String url){
        super();
        this.id = id;
        this.url = url;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

}