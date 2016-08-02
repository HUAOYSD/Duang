package org.duang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MemberExtraInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "member_extra_info", catalog = "duang")
public class MemberExtraInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String address;
	private Integer degree;
	private Integer jobType;
	private String school;
	private String salaryYear;
	private String hobby;

	// Constructors

	/** default constructor */
	public MemberExtraInfo() {
	}

	/** minimal constructor */
	public MemberExtraInfo(String id, String userId) {
		this.id = id;
		this.userId = userId;
	}

	/** full constructor */
	public MemberExtraInfo(String id, String userId, String address, Integer degree, Integer jobType, String school, String salaryYear, String hobby) {
		this.id = id;
		this.userId = userId;
		this.address = address;
		this.degree = degree;
		this.jobType = jobType;
		this.school = school;
		this.salaryYear = salaryYear;
		this.hobby = hobby;
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

	@Column(name = "user_id", nullable = false, length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "address", length = 500)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "degree")
	public Integer getDegree() {
		return this.degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	@Column(name = "job_type")
	public Integer getJobType() {
		return this.jobType;
	}

	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}

	@Column(name = "school")
	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "salary_year")
	public String getSalaryYear() {
		return this.salaryYear;
	}

	public void setSalaryYear(String salaryYear) {
		this.salaryYear = salaryYear;
	}

	@Column(name = "hobby")
	public String getHobby() {
		return this.hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

}