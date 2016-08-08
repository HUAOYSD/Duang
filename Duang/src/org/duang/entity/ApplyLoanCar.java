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
 * ApplyLoanCar entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "apply_loan_car", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ApplyLoanCar implements java.io.Serializable {

	// Fields

	private String id;
	private LoanList loanList;
	private String name;
	private String idcard;
	private String phone;
	private String province;
	private String city;
	private String brand;
	private String age;
	private String limit;
	private String engine;
	private String use;
	private String carProperty;

	// Constructors

	/** default constructor */
	public ApplyLoanCar() {
	}

	/** minimal constructor */
	public ApplyLoanCar(String id) {
		this.id = id;
	}

	/** full constructor */
	public ApplyLoanCar(String id, LoanList loanList, String name,
			String idcard, String phone, String province, String city,
			String brand, String age, String limit, String engine, String use,
			String carProperty) {
		this.id = id;
		this.loanList = loanList;
		this.name = name;
		this.idcard = idcard;
		this.phone = phone;
		this.province = province;
		this.city = city;
		this.brand = brand;
		this.age = age;
		this.limit = limit;
		this.engine = engine;
		this.use = use;
		this.carProperty = carProperty;
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

	@Column(name = "brand", length = 30)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "age", length = 20)
	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Column(name = "limit", length = 20)
	public String getLimit() {
		return this.limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	@Column(name = "engine", length = 35)
	public String getEngine() {
		return this.engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	@Column(name = "use", length = 16777215)
	public String getUse() {
		return this.use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	@Column(name = "car_property", length = 16777215)
	public String getCarProperty() {
		return this.carProperty;
	}

	public void setCarProperty(String carProperty) {
		this.carProperty = carProperty;
	}

}