package org.duang.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

/**
 * LoanType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "loan_type", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class LoanType implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String remark;
	private int module;
	private Date createtime;
	private Set<LoanList> loanLists = new HashSet<LoanList>(0);

	// Constructors

	/** default constructor */
	public LoanType() {
	}

	/** minimal constructor */
	public LoanType(String id) {
		this.id = id;
	}

	/** full constructor */
	public LoanType(String id, String name, String remark, int module,
			Date createtime, Set<LoanList> loanLists) {
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.module = module;
		this.createtime = createtime;
		this.loanLists = loanLists;
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

	@Column(name = "name", length = 300)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "remark", length = 16777215)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "module")
	public int getModule() {
		return this.module;
	}

	public void setModule(int module) {
		this.module = module;
	}

	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "loanType")
	public Set<LoanList> getLoanLists() {
		return this.loanLists;
	}

	public void setLoanLists(Set<LoanList> loanLists) {
		this.loanLists = loanLists;
	}

}