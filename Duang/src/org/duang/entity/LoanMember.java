package org.duang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicInsert;

/**
 * LoanMember entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "loan_member", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class LoanMember implements java.io.Serializable {

	// Fields

	private String id;
	private MemberInfo memberInfo;
	
	/**
	 * 总借款
	 */
	private double lendMoney;
	/**
	 * 当前借款
	 */
	private double curMoney;
	/**
	 * 总还款
	 */
	private double backMoney;
	/**
	 * 剩余应还
	 */
	private double residueMoney;
	/**
	 * 总逾期
	 */
	private double expectMoney;

	// Constructors

	/** default constructor */
	public LoanMember() {
	}

	/** minimal constructor */
	public LoanMember(String id, MemberInfo memberInfo) {
		this.id = id;
		this.memberInfo = memberInfo;
	}

	/** full constructor */
	public LoanMember(String id, 
			MemberInfo memberInfo, double lendMoney,double curMoney, double backMoney,
			double residueMoney, double expectMoney) {
		this.id = id;
		this.memberInfo = memberInfo;
		this.lendMoney = lendMoney;
		this.curMoney = curMoney;
		this.backMoney = backMoney;
		this.residueMoney = residueMoney;
		this.expectMoney = expectMoney;
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


	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "member_info_id", nullable = false)
	public MemberInfo getMemberInfo() {
		return this.memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	@Column(name = "lend_money", precision = 22, scale = 0)
	public double getLendMoney() {
		return this.lendMoney;
	}

	public void setLendMoney(double lendMoney) {
		this.lendMoney = lendMoney;
	}

	@Column(name = "cur_money", precision = 22, scale = 0)
	public double getCurMoney() {
		return this.curMoney;
	}

	public void setCurMoney(double curMoney) {
		this.curMoney = curMoney;
	}
	
	@Column(name = "back_money", precision = 22, scale = 0)
	public double getBackMoney() {
		return this.backMoney;
	}

	public void setBackMoney(double backMoney) {
		this.backMoney = backMoney;
	}

	@Column(name = "residue_money", precision = 22, scale = 0)
	public double getResidueMoney() {
		return this.residueMoney;
	}

	public void setResidueMoney(double residueMoney) {
		this.residueMoney = residueMoney;
	}

	@Column(name = "expect_money", precision = 22, scale = 0)
	public double getExpectMoney() {
		return this.expectMoney;
	}

	public void setExpectMoney(double expectMoney) {
		this.expectMoney = expectMoney;
	}




}