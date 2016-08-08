package org.duang.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

/**
 * CustomerManager entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer_manager", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class CustomerManager implements java.io.Serializable {

	// Fields

	private String id;
	private SysUser sysUser;
	private String name;
	private String workNumber;
	private String sex;
	private String idcard;
	private String email;
	private String phone;
	private String photo;
	private String qr;
	private String remark;
	private Set<LoanMember> loanMembers = new HashSet<LoanMember>(0);
	private Set<InvestMember> investMembers = new HashSet<InvestMember>(0);

	// Constructors

	/** default constructor */
	public CustomerManager() {
	}

	/** minimal constructor */
	public CustomerManager(String id) {
		this.id = id;
	}

	/** full constructor */
	public CustomerManager(String id, SysUser sysUser, String name,
			String workNumber, String sex, String idcard, String email,
			String phone, String photo, String qr, String remark,
			Set<LoanMember> loanMembers, Set<InvestMember> investMembers) {
		this.id = id;
		this.sysUser = sysUser;
		this.name = name;
		this.workNumber = workNumber;
		this.sex = sex;
		this.idcard = idcard;
		this.email = email;
		this.phone = phone;
		this.photo = photo;
		this.qr = qr;
		this.remark = remark;
		this.loanMembers = loanMembers;
		this.investMembers = investMembers;
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
	@JoinColumn(name = "user_id")
	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "work_number", length = 500)
	public String getWorkNumber() {
		return this.workNumber;
	}

	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	@Column(name = "sex", length = 10)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "idcard", length = 20)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone", length = 15)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "photo", length = 500)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name = "qr", length = 500)
	public String getQr() {
		return this.qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	@Column(name = "remark", length = 16777215)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerManager")
	public Set<LoanMember> getLoanMembers() {
		return this.loanMembers;
	}

	public void setLoanMembers(Set<LoanMember> loanMembers) {
		this.loanMembers = loanMembers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerManager")
	public Set<InvestMember> getInvestMembers() {
		return this.investMembers;
	}

	public void setInvestMembers(Set<InvestMember> investMembers) {
		this.investMembers = investMembers;
	}

}