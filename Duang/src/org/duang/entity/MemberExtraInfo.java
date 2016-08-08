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
 * MemberExtraInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "member_extra_info", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class MemberExtraInfo implements java.io.Serializable {

	// Fields

	private String id;
	private MemberInfo memberInfo;
	private String address;
	private String degree;
	private String jobType;
	private String school;
	private String salaryYear;
	private String hobby;

	// Constructors

	/** default constructor */
	public MemberExtraInfo() {
	}

	/** minimal constructor */
	public MemberExtraInfo(String id, MemberInfo memberInfo) {
		this.id = id;
		this.memberInfo = memberInfo;
	}

	/** full constructor */
	public MemberExtraInfo(String id, MemberInfo memberInfo, String address,
			String degree, String jobType, String school, String salaryYear,
			String hobby) {
		this.id = id;
		this.memberInfo = memberInfo;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_info_id", nullable = false)
	public MemberInfo getMemberInfo() {
		return this.memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	@Column(name = "address", length = 500)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "degree", length = 20)
	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Column(name = "job_type", length = 50)
	public String getJobType() {
		return this.jobType;
	}

	public void setJobType(String jobType) {
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

	@Column(name = "hobby", length = 500)
	public String getHobby() {
		return this.hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

}