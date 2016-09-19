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
@Table(name = "sms", catalog = "duang")
@DynamicInsert(true)
public class SMS implements Serializable {
	
	private String id;
	private String phone;
	private String content;
	private Date createTime;
	private int state; //状态 0删除，1正常
	private int platform;
	private int mesNum;
	private double price;
	private double money;
	// Constructors

	/** default constructor */
	public SMS() {
	}

	/** minimal constructor */
	public SMS(String id) {
		this.id = id;
	}

	
	public SMS(String id, String phone, String content, Date createTime, int state, int platform, int mesNum, double price, double money) {
		super();
		this.id = id;
		this.phone = phone;
		this.content = content;
		this.createTime = createTime;
		this.state = state;
		this.platform = platform;
		this.mesNum = mesNum;
		this.price = price;
		this.money = money;
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
	@Column(name = "state")
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "content", length = 500)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "platform")
	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

	@Column(name = "mesNum")
	public int getMesNum() {
		return mesNum;
	}

	public void setMesNum(int mesNum) {
		this.mesNum = mesNum;
	}

	@Column(name = "price", precision = 22, scale = 0)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "money", precision = 22, scale = 0)
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
}