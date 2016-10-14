package org.duang.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NewsInformation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news_information", catalog = "duang")
public class NewsInformation implements java.io.Serializable {
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	private String id;
	private String content;
	private Date createtime;
	private String img;
	private String spare2;

	// Constructors

	/** default constructor */
	public NewsInformation() {
	}

	/** minimal constructor */
	public NewsInformation(String id, String content, Date createtime) {
		this.id = id;
		this.content = content;
		this.createtime = createtime;
	}

	/** full constructor */
	public NewsInformation(String id, String content, Date createtime, String img, String spare2) {
		this.id = id;
		this.content = content;
		this.createtime = createtime;
		this.img = img;
		this.spare2 = spare2;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "content", nullable = false, length = 16777215)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "img")
	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(name = "spare2")
	public String getSpare2() {
		return this.spare2;
	}

	public void setSpare2(String spare2) {
		this.spare2 = spare2;
	}

}