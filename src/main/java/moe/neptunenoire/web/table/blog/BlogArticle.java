package moe.neptunenoire.web.table.blog;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="blogarticle", indexes = {@Index(name = "user_acticleIndex", columnList="title,articleid,userid")})
public class BlogArticle {

	/**
	 * 文章ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "articleid")
	private Integer articleid;

	/**
	 * 文章作者
	 */
	@Column(name = "userid")
	private Integer userid;

	/**
	 * 文章标题
	 */
	@Column(name = "title")
	private String title;

	/**
	 * 文章正体
	 */
	@Column(name = "article")
	private String article;

	/**
	 * 时间
	 */
	@Column(name="date_new")
	@CreationTimestamp
	private Date date_new;
	/*
	 * ===================================================
	 */
	public Integer getArticleid() {
		return articleid;
	}

	public void setArticleid(Integer articleid) {
		this.articleid = articleid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Date getDate_new() {
		return date_new;
	}

	public void setDate_new(Date date_new) {
		this.date_new = date_new;
	}

	public BlogArticle(Integer articleid, Integer userid, String title, String article, Date date_new) {
		super();
		this.articleid = articleid;
		this.userid = userid;
		this.title = title;
		this.article = article;
		this.date_new = date_new;
	}

	public BlogArticle() {
		super();
	}

	@Override
	public String toString() {
		return "BlogArticle [articleid=" + articleid + ", userid=" + userid + ", title=" + title + ", article="
				+ article + ", date_new=" + date_new + "]";
	}
}
