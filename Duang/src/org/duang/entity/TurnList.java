package org.duang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

/**
 * TurnList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "turn_list", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TurnList implements java.io.Serializable {

	// Fields

	private String id;
	private InvestList investList;
	private int type;
	private double poundage;
	private double couponPoundage;
	private Date createtime;
	private Date donetime;
	private int status;

	// Constructors

	/** default constructor */
	public TurnList() {
	}

	/** minimal constructor */
	public TurnList(String id) {
		this.id = id;
	}

	/** full constructor */
	public TurnList(String id, InvestList investList, int type,
			double poundage, double couponPoundage, Date createtime,
			Date donetime, int status) {
		this.id = id;
		this.investList = investList;
		this.type = type;
		this.poundage = poundage;
		this.couponPoundage = couponPoundage;
		this.createtime = createtime;
		this.donetime = donetime;
		this.status = status;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invest_list")
	public InvestList getInvestList() {
		return this.investList;
	}

	public void setInvestList(InvestList investList) {
		this.investList = investList;
	}

	@Column(name = "type")
	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "poundage", precision = 22, scale = 0)
	public double getPoundage() {
		return this.poundage;
	}

	public void setPoundage(double poundage) {
		this.poundage = poundage;
	}

	@Column(name = "coupon_poundage", precision = 22, scale = 0)
	public double getCouponPoundage() {
		return this.couponPoundage;
	}

	public void setCouponPoundage(double couponPoundage) {
		this.couponPoundage = couponPoundage;
	}

	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "donetime", length = 19)
	public Date getDonetime() {
		return this.donetime;
	}

	public void setDonetime(Date donetime) {
		this.donetime = donetime;
	}

	@Column(name = "status")
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}