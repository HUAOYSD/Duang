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
 * Stock entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "stock", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class Stock implements java.io.Serializable {

	// Fields

	private String id;
	private LoanMember loanMember;
	private Scale scale;
	private InvestMember investMember;
	private double money;
	private double fetch;
	private Date createTime;
	private Date fetchTime;
	private double difference;
	private int status;

	// Constructors

	/** default constructor */
	public Stock() {
	}

	/** minimal constructor */
	public Stock(String id) {
		this.id = id;
	}

	/** full constructor */
	public Stock(String id, LoanMember loanMember, Scale scale,
			InvestMember investMember, double money, double fetch,
			Date createTime, Date fetchTime, double difference,
			int status) {
		this.id = id;
		this.loanMember = loanMember;
		this.scale = scale;
		this.investMember = investMember;
		this.money = money;
		this.fetch = fetch;
		this.createTime = createTime;
		this.fetchTime = fetchTime;
		this.difference = difference;
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
	@JoinColumn(name = "loan_member_id")
	public LoanMember getLoanMember() {
		return this.loanMember;
	}

	public void setLoanMember(LoanMember loanMember) {
		this.loanMember = loanMember;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scale_id")
	public Scale getScale() {
		return this.scale;
	}

	public void setScale(Scale scale) {
		this.scale = scale;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fetch_member")
	public InvestMember getInvestMember() {
		return this.investMember;
	}

	public void setInvestMember(InvestMember investMember) {
		this.investMember = investMember;
	}

	@Column(name = "money", precision = 22, scale = 0)
	public double getMoney() {
		return this.money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Column(name = "fetch", precision = 22, scale = 0)
	public double getFetch() {
		return this.fetch;
	}

	public void setFetch(double fetch) {
		this.fetch = fetch;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "fetch_time", length = 19)
	public Date getFetchTime() {
		return this.fetchTime;
	}

	public void setFetchTime(Date fetchTime) {
		this.fetchTime = fetchTime;
	}

	@Column(name = "difference", precision = 22, scale = 0)
	public double getDifference() {
		return this.difference;
	}

	public void setDifference(double difference) {
		this.difference = difference;
	}

	@Column(name = "status")
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}