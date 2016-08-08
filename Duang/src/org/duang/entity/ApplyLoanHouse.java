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
 * ApplyLoanHouse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "apply_loan_house", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ApplyLoanHouse implements java.io.Serializable {

	// Fields

	private String id;
	private LoanList loanList;
	private String name;
	private String idcard;
	private String phone;
	private String province;
	private String city;
	private String houseNumber;
	private String datums;
	private String assetCertificates;
	private double money;

	// Constructors

	/** default constructor */
	public ApplyLoanHouse() {
	}

	/** minimal constructor */
	public ApplyLoanHouse(String id) {
		this.id = id;
	}

	/** full constructor */
	public ApplyLoanHouse(String id, LoanList loanList, String name,
			String idcard, String phone, String province, String city,
			String houseNumber, String datums, String assetCertificates,
			double money) {
		this.id = id;
		this.loanList = loanList;
		this.name = name;
		this.idcard = idcard;
		this.phone = phone;
		this.province = province;
		this.city = city;
		this.houseNumber = houseNumber;
		this.datums = datums;
		this.assetCertificates = assetCertificates;
		this.money = money;
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

	@Column(name = "province", length = 30)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", length = 30)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "house_number", length = 50)
	public String getHouseNumber() {
		return this.houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	@Column(name = "datums", length = 16777215)
	public String getDatums() {
		return this.datums;
	}

	public void setDatums(String datums) {
		this.datums = datums;
	}

	@Column(name = "asset_certificates", length = 16777215)
	public String getAssetCertificates() {
		return this.assetCertificates;
	}

	public void setAssetCertificates(String assetCertificates) {
		this.assetCertificates = assetCertificates;
	}

	@Column(name = "money", precision = 22, scale = 0)
	public double getMoney() {
		return this.money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

}