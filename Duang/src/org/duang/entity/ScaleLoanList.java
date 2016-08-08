package org.duang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

/**
 * ScaleLoanList entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "scale_loan_list", catalog = "duang")
@DynamicInsert(true)
public class ScaleLoanList implements java.io.Serializable {

	// Fields

	private String id;
	private Scale scale;
	private LoanList loanList;

	// Constructors

	/** default constructor */
	public ScaleLoanList() {
	}

	/** minimal constructor */
	public ScaleLoanList(String id) {
		this.id = id;
	}

	/** full constructor */
	public ScaleLoanList(String id, Scale scale, LoanList loanList) {
		this.id = id;
		this.scale = scale;
		this.loanList = loanList;
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
	@JoinColumn(name = "scale")
	public Scale getScale() {
		return this.scale;
	}

	public void setScale(Scale scale) {
		this.scale = scale;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loan_list")
	public LoanList getLoanList() {
		return this.loanList;
	}

	public void setLoanList(LoanList loanList) {
		this.loanList = loanList;
	}

}