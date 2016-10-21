package org.duang.entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

/**
 * InvestMember entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "invest_member", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class InvestMember implements java.io.Serializable {

	// Fields

	private String id;
	private MemberInfo memberInfo;
	private int isContract;
	private double balance;
	private double investing;
	private double totalIncome;
	private double totalMoney;
	private double currentIncome;

	// Constructors

	/** default constructor */
	public InvestMember() {
	}

	/** minimal constructor */
	public InvestMember(String id, MemberInfo memberInfo) {
		this.id = id;
		this.memberInfo = memberInfo;
	}

	/** full constructor */
	public InvestMember(String id, MemberInfo memberInfo,
			 double currentIncome,
			int isContract, double balance, double investing,
			double totalIncome, double totalMoney) {
		this.id = id;
		this.memberInfo = memberInfo;
		this.isContract = isContract;
		this.balance = balance;
		this.investing = investing;
		this.totalIncome = totalIncome;
		this.totalMoney = totalMoney;
		this.currentIncome = currentIncome;
		//this.stocks = stocks;
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

	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "memberinfo_id", nullable = false)
	public MemberInfo getMemberInfo() {
		return this.memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}
	@Column(name = "is_contract")
	public int getIsContract() {
		return this.isContract;
	}

	public void setIsContract(int isContract) {
		this.isContract = isContract;
	}

	@Column(name = "balance", precision = 22, scale = 0)
	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Column(name = "investing", precision = 22, scale = 0)
	public double getInvesting() {
		return this.investing;
	}

	public void setInvesting(double investing) {
		this.investing = investing;
	}

	@Column(name = "total_income", precision = 22, scale = 0)
	public double getTotalIncome() {
		return this.totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	@Column(name = "total_money", precision = 22, scale = 0)
	public double getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Column(name = "current_income", precision = 22, scale = 0)
	public double getCurrentIncome() {
		return this.currentIncome;
	}

	public void setCurrentIncome(double currentIncome) {
		this.currentIncome = currentIncome;
	}

}