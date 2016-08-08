package org.duang.entity;

import java.util.Date;
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
 * BindCard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bind_card", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class BindCard implements java.io.Serializable {

	// Fields

	private String id;
	private MemberInfo memberInfo;
	private String name;
	private String idcard;
	private String phone;
	private String bankNo;
	private String openBank;
	private int type;
	private String photoImg1;
	private String photoImg2;
	private Date optTime;
	private Set<BillInvest> billInvests = new HashSet<BillInvest>(0);
	private Set<BillLoan> billLoans = new HashSet<BillLoan>(0);

	// Constructors

	/** default constructor */
	public BindCard() {
	}

	/** minimal constructor */
	public BindCard(String id) {
		this.id = id;
	}

	/** full constructor */
	public BindCard(String id, MemberInfo memberInfo, String name,
			String idcard, String phone, String bankNo, String openBank,
			int type, String photoImg1, String photoImg2,
			Date optTime, Set<BillInvest> billInvests,
			Set<BillLoan> billLoans) {
		this.id = id;
		this.memberInfo = memberInfo;
		this.name = name;
		this.idcard = idcard;
		this.phone = phone;
		this.bankNo = bankNo;
		this.openBank = openBank;
		this.type = type;
		this.photoImg1 = photoImg1;
		this.photoImg2 = photoImg2;
		this.optTime = optTime;
		this.billInvests = billInvests;
		this.billLoans = billLoans;
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
	@JoinColumn(name = "member_info_id")
	public MemberInfo getMemberInfo() {
		return this.memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "idcard", length = 20)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "phone", length = 15)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "bank_no", length = 20)
	public String getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	@Column(name = "open_bank", length = 90)
	public String getOpenBank() {
		return this.openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	@Column(name = "type")
	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "photo_img1", length = 500)
	public String getPhotoImg1() {
		return this.photoImg1;
	}

	public void setPhotoImg1(String photoImg1) {
		this.photoImg1 = photoImg1;
	}

	@Column(name = "photo_img2", length = 500)
	public String getPhotoImg2() {
		return this.photoImg2;
	}

	public void setPhotoImg2(String photoImg2) {
		this.photoImg2 = photoImg2;
	}

	@Column(name = "opt_time", length = 19)
	public Date getOptTime() {
		return this.optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bindCard")
	public Set<BillInvest> getBillInvests() {
		return this.billInvests;
	}

	public void setBillInvests(Set<BillInvest> billInvests) {
		this.billInvests = billInvests;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bindCard")
	public Set<BillLoan> getBillLoans() {
		return this.billLoans;
	}

	public void setBillLoans(Set<BillLoan> billLoans) {
		this.billLoans = billLoans;
	}

}