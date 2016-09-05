package org.duang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

/**
 * LoanList entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ad", catalog = "duang")
@DynamicInsert(true)
public class Ad implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String remark;
	private Date createTime;
	private String imageAddress;
	private String linkAddress;
	private int isUse;
	// Constructors

	/** default constructor */
	public Ad() {
	}

	/** minimal constructor */
	public Ad(String id) {
		this.id = id;
	}

	/** full constructor */
	public Ad(String id, String name, String remark, Date createTime,
			String imageAddress, String linkAddress) {
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.createTime = createTime;
		this.imageAddress = imageAddress;
		this.linkAddress = linkAddress;
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

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "image_address")
	public String getImageAddress() {
		return imageAddress;
	}
	public void setImageAddress(String imageAddress) {
		this.imageAddress = imageAddress;
	}

	@Column(name = "link_address")
	public String getLinkAddress() {
		return linkAddress;
	}
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}
	
	@Column(name = "is_use")
	public int getIsUse() {
		return isUse;
	}
	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}
}