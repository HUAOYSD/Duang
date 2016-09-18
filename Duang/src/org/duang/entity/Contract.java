package org.duang.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@SuppressWarnings("serial")
@Entity
@Table(name = "contract", catalog = "duang")
@DynamicInsert(true)
public class Contract implements Serializable {

	// Fields

	private String id;
	private String name;
	private String conPath;
	private Date createTime;
	private int state; //状态 0删除，1正常
	// Constructors

	/** default constructor */
	public Contract() {
	}

	/** minimal constructor */
	public Contract(String id) {
		this.id = id;
	}

	/** full constructor */
	public Contract(String id, String name, String conPath, Date createTime,int state) {
		this.id = id;
		this.name = name;
		this.conPath = conPath;
		this.createTime = createTime;
		this.state = state;
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

	@Column(name = "createtime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "conPath")
	public String getConPath() {
		return conPath;
	}
	public void setConPath(String conPath) {
		this.conPath = conPath;
	}
	
	@Column(name = "state")
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}