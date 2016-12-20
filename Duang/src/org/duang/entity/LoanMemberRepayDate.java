package org.duang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

/**
 * 用户的还款日，赎回日期
 * @ClassName:  LoanMemberRepayDate   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年12月15日 下午5:38:56
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "loan_member_repay_date", catalog = "duang")
@DynamicInsert(true)
public class LoanMemberRepayDate implements java.io.Serializable {

	// Fields

	private String id;
	private String loanListId;
	private Date repayDate;
	private int repayIndex;
	private int state;
	private int status;
	private int isOver;
	private int overDays;
	private Date reRepayDate;
	private double overSum;
	// Constructors

	/** default constructor */
	public LoanMemberRepayDate() {
	}

	/** minimal constructor */
	public LoanMemberRepayDate(String id) {
		this.id = id;
	}

	public LoanMemberRepayDate(String id, String loanListId, Date repayDate, int repayIndex, int state, int status) {
		super();
		this.id = id;
		this.loanListId = loanListId;
		this.repayDate = repayDate;
		this.repayIndex = repayIndex;
		this.state = state;
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

	@Column(name = "repayDate", length = 19)
	public Date getRepayDate() {
		return this.repayDate;
	}
	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}

	@Column(name = "loan_list_id")
	public String getLoanListId() {
		return loanListId;
	}
	public void setLoanListId(String loanListId) {
		this.loanListId = loanListId;
	}
	
	@Column(name = "repayIndex")
	public int getRepayIndex() {
		return repayIndex;
	}
	public void setRepayIndex(int repayIndex) {
		this.repayIndex = repayIndex;
	}
	
	@Column(name = "state")
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	@Column(name = "status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "is_over")
	public int getIsOver() {
		return isOver;
	}

	public void setIsOver(int isOver) {
		this.isOver = isOver;
	}

	@Column(name = "over_days")
	public int getOverDays() {
		return overDays;
	}

	public void setOverDays(int overDays) {
		this.overDays = overDays;
	}

	@Column(name = "re_repay_date", length = 19)
	public Date getReRepayDate() {
		return reRepayDate;
	}

	public void setReRepayDate(Date reRepayDate) {
		this.reRepayDate = reRepayDate;
	}

	@Column(name = "over_sum")
	public double getOverSum() {
		return overSum;
	}

	public void setOverSum(double overSum) {
		this.overSum = overSum;
	}
	
}