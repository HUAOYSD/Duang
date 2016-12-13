package org.duang.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * LoanMemberExtraInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "loan_member_extra_info", catalog = "duang")
public class LoanMemberExtraInfo implements java.io.Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	private String id;
	private String memberId;
	private String compName;
	private String compAddress;
	private String compType;
	private String compPhone;
	private String jobDepart;
	private Date jobEntryTime;
	private String jobIncome;
	private Date jobSalaryDate;
	private String familyName;
	private String familyType;
	private String familyPhone;
	private String familyCompName;
	private String compColleName;
	private String compCollePost;
	private String compCollePhone;
	private String otherName;
	private String otherType;
	private String otherPhone;
	private String otherComp;

	// Constructors

	/** default constructor */
	public LoanMemberExtraInfo() {
	}

	/** minimal constructor */
	public LoanMemberExtraInfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public LoanMemberExtraInfo(String id, String memberId, String compName, String compAddress, String compType, String compPhone, String jobDepart, Date jobEntryTime, String jobIncome, Date jobSalaryDate, String familyName, String familyType, String familyPhone, String familyCompName, String compColleName, String compCollePost, String compCollePhone, String otherName, String otherType, String otherPhone, String otherComp) {
		this.id = id;
		this.memberId = memberId;
		this.compName = compName;
		this.compAddress = compAddress;
		this.compType = compType;
		this.compPhone = compPhone;
		this.jobDepart = jobDepart;
		this.jobEntryTime = jobEntryTime;
		this.jobIncome = jobIncome;
		this.jobSalaryDate = jobSalaryDate;
		this.familyName = familyName;
		this.familyType = familyType;
		this.familyPhone = familyPhone;
		this.familyCompName = familyCompName;
		this.compColleName = compColleName;
		this.compCollePost = compCollePost;
		this.compCollePhone = compCollePhone;
		this.otherName = otherName;
		this.otherType = otherType;
		this.otherPhone = otherPhone;
		this.otherComp = otherComp;
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

	@Column(name = "member_id", length = 36)
	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Column(name = "comp_name")
	public String getCompName() {
		return this.compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	@Column(name = "comp_address")
	public String getCompAddress() {
		return this.compAddress;
	}

	public void setCompAddress(String compAddress) {
		this.compAddress = compAddress;
	}

	@Column(name = "comp_type")
	public String getCompType() {
		return this.compType;
	}

	public void setCompType(String compType) {
		this.compType = compType;
	}

	@Column(name = "comp_phone")
	public String getCompPhone() {
		return this.compPhone;
	}

	public void setCompPhone(String compPhone) {
		this.compPhone = compPhone;
	}

	@Column(name = "job_depart")
	public String getJobDepart() {
		return this.jobDepart;
	}

	public void setJobDepart(String jobDepart) {
		this.jobDepart = jobDepart;
	}

	@Column(name = "job_entry_time", length = 10)
	public Date getJobEntryTime() {
		return this.jobEntryTime;
	}

	public void setJobEntryTime(Date jobEntryTime) {
		this.jobEntryTime = jobEntryTime;
	}

	@Column(name = "job_income")
	public String getJobIncome() {
		return this.jobIncome;
	}

	public void setJobIncome(String jobIncome) {
		this.jobIncome = jobIncome;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "job_salary_date", length = 10)
	public Date getJobSalaryDate() {
		return this.jobSalaryDate;
	}

	public void setJobSalaryDate(Date jobSalaryDate) {
		this.jobSalaryDate = jobSalaryDate;
	}

	@Column(name = "family_name")
	public String getFamilyName() {
		return this.familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	@Column(name = "family_type")
	public String getFamilyType() {
		return this.familyType;
	}

	public void setFamilyType(String familyType) {
		this.familyType = familyType;
	}

	@Column(name = "family_phone")
	public String getFamilyPhone() {
		return this.familyPhone;
	}

	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}

	@Column(name = "family_comp_name")
	public String getFamilyCompName() {
		return this.familyCompName;
	}

	public void setFamilyCompName(String familyCompName) {
		this.familyCompName = familyCompName;
	}

	@Column(name = "comp_colle_name")
	public String getCompColleName() {
		return this.compColleName;
	}

	public void setCompColleName(String compColleName) {
		this.compColleName = compColleName;
	}

	@Column(name = "comp_colle_post")
	public String getCompCollePost() {
		return this.compCollePost;
	}

	public void setCompCollePost(String compCollePost) {
		this.compCollePost = compCollePost;
	}

	@Column(name = "comp_colle_phone")
	public String getCompCollePhone() {
		return this.compCollePhone;
	}

	public void setCompCollePhone(String compCollePhone) {
		this.compCollePhone = compCollePhone;
	}

	@Column(name = "other_name")
	public String getOtherName() {
		return this.otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	@Column(name = "other_type")
	public String getOtherType() {
		return this.otherType;
	}

	public void setOtherType(String otherType) {
		this.otherType = otherType;
	}

	@Column(name = "other_phone")
	public String getOtherPhone() {
		return this.otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	@Column(name = "other_comp")
	public String getOtherComp() {
		return this.otherComp;
	}

	public void setOtherComp(String otherComp) {
		this.otherComp = otherComp;
	}

}