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
 * BillInvest entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bill_invest", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class BillInvest implements java.io.Serializable {

	// Fields

	private String id;
	private InvestMember investMember;
	private InvestList investList;
	private BindCard bindCard;
	private int useType;
	private double money;
	private double balance;
	private double asset;
	private int status;
	private Date optTime;
	private String remark;
	private int style;

	// Constructors

	/** default constructor */
	public BillInvest() {
	}

	/** minimal constructor */
	public BillInvest(String id) {
		this.id = id;
	}

	/** full constructor */
	public BillInvest(String id, InvestMember investMember,
			InvestList investList, BindCard bindCard, int useType,
			double money, double balance, double asset, int status,
			Date optTime, String remark, int style) {
		this.id = id;
		this.investMember = investMember;
		this.investList = investList;
		this.bindCard = bindCard;
		this.useType = useType;
		this.money = money;
		this.balance = balance;
		this.asset = asset;
		this.status = status;
		this.optTime = optTime;
		this.remark = remark;
		this.style = style;
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
	@JoinColumn(name = "invest_member_id")
	public InvestMember getInvestMember() {
		return this.investMember;
	}

	public void setInvestMember(InvestMember investMember) {
		this.investMember = investMember;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invest_list_id")
	public InvestList getInvestList() {
		return this.investList;
	}

	public void setInvestList(InvestList investList) {
		this.investList = investList;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "card")
	public BindCard getBindCard() {
		return this.bindCard;
	}

	public void setBindCard(BindCard bindCard) {
		this.bindCard = bindCard;
	}

	@Column(name = "use_type")
	public int getUseType() {
		return this.useType;
	}

	public void setUseType(int useType) {
		this.useType = useType;
	}

	@Column(name = "money", precision = 22, scale = 0)
	public double getMoney() {
		return this.money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Column(name = "balance", precision = 22, scale = 0)
	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Column(name = "asset", precision = 22, scale = 0)
	public double getAsset() {
		return this.asset;
	}

	public void setAsset(double asset) {
		this.asset = asset;
	}

	@Column(name = "status")
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
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