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
 * BillLoan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bill_loan", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class BillLoan implements java.io.Serializable {

	// Fields

	private String id;
	private LoanList loanList;
	private BindCard bindCard;
	private MemberInfo memberInfo;
	private int status;
	private double money;
	private double doneMoney;
	private int billStatus;
	private Date optTime;
	private String remark;
	private int style;

	// Constructors

	/** default constructor */
	public BillLoan() {
	}

	/** minimal constructor */
	public BillLoan(String id) {
		this.id = id;
	}

	/** full constructor */
	public BillLoan(String id, LoanList loanList, BindCard bindCard,
			int status, double money, double doneMoney, int billStatus,MemberInfo memberInfo,
			Date optTime, String remark, int style) {
		this.id = id;
		this.loanList = loanList;
		this.bindCard = bindCard;
		this.status = status;
		this.money = money;
		this.doneMoney = doneMoney;
		this.billStatus = billStatus;
		this.optTime = optTime;
		this.remark = remark;
		this.style = style;
		this.memberInfo = memberInfo;
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
	@JoinColumn(name = "loan_list_id")
	public LoanList getLoanList() {
		return this.loanList;
	}

	public void setLoanList(LoanList loanList) {
		this.loanList = loanList;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "card")
	public BindCard getBindCard() {
		return this.bindCard;
	}

	public void setBindCard(BindCard bindCard) {
		this.bindCard = bindCard;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_info")
	public MemberInfo getMemberInfo() {
		return this.memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}
	
	@Column(name = "status")
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "money", precision = 22, scale = 0)
	public double getMoney() {
		return this.money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Column(name = "done_money", precision = 22, scale = 0)
	public double getDoneMoney() {
		return this.doneMoney;
	}

	public void setDoneMoney(double doneMoney) {
		this.doneMoney = doneMoney;
	}

	@Column(name = "bill_status")
	public int getBillStatus() {
		return this.billStatus;
	}

	public void setBillStatus(int billStatus) {
		this.billStatus = billStatus;
	}

	@Column(name = "opt_time", length = 19)
	public Date getOptTime() {
		return this.optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	@Column(name = "remark", length = 3000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "style")
	public int getStyle() {
		return this.style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

}