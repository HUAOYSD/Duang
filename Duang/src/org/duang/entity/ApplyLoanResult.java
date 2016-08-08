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
 * ApplyLoanResult entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "apply_loan_result", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ApplyLoanResult implements java.io.Serializable {

	// Fields

	private String id;
	private LoanList loanList;
	private int state;
	private String content;
	private Date optTime;

	// Constructors

	/** default constructor */
	public ApplyLoanResult() {
	}

	/** minimal constructor */
	public ApplyLoanResult(String id) {
		this.id = id;
	}

	/** full constructor */
	public ApplyLoanResult(String id, LoanList loanList, int state,
			String content, Date optTime) {
		this.id = id;
		this.loanList = loanList;
		this.state = state;
		this.content = content;
		this.optTime = optTime;
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

	@Column(name = "state")
	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Column(name = "content", length = 16777215)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "opt_time", length = 19)
	public Date getOptTime() {
		return this.optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

}