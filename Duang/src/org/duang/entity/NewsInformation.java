package org.duang.entity;

import java.sql.Timestamp;
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
	private Timestamp createtime;
	private String spare1;
	private String spare2;

	// Constructors

	/** default constructor */
	public NewsInformation() {
	}

	/** minimal constructor */
	public NewsInformation(String id, String content, Timestamp createtime) {
		this.id = id;
		this.content = content;
		this.createtime = createtime;
	}

	/** full constructor */
	public NewsInformation(String id, String content, Timestamp createtime, String spare1, String spare2) {
		this.id = id;
		this.content = content;
		this.createtime = createtime;
		this.spare1 = spare1;
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
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "spare1")
	public String getSpare1() {
		return this.spare1;
	}

	public void setSpare1(String spare1) {
		this.spare1 = spare1;
	}

	@Column(name = "spare2")
	public String getSpare2() {
		return this.spare2;
	}

	public void setSpare2(String spare2) {
		this.spare2 = spare2;
	}

}