package org.duang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;

/**
 * ApplyLoanInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "apply_loan_info", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ApplyLoanInfo implements java.io.Serializable {

	// Fields

	private String id;
	private LoanList loanList;
	//贷款金额
	private double money;
	//贷款期限(单位月)
	private double timeLimit;
	private String sex;
	private String name;
	private String phone;
	private String idcard;
	private String email;
	//户籍所在地
	private String nativeAddress;
	//户籍详信息
	private String nativeInfo;
	//现在居住地
	private String address;
	//住房方式，租房和自居房 0:租房 1:自居房
	private String liveStyle;
	//学历 [0专科  1本科  2硕士研究生 3博士研究生]
	private String education;
	//婚姻状况 0未婚 1已婚
	private String marriage;
	//房子状况
	private String house;
	//有无信用卡 0没有  1有
	private String hasCredit;
	private String creditCard;
	//工作行业
	private String industry;
	//职业性质 0兼职 1全职
	private String jobStyle;
	//担任职务
	private String job;
	private String jobCity;
	private String company;
	private String publicTel;
	//工资是否属于银行代发 0不是 1是
	private String salaryFromBank;
	//年收入
	private String yearIncome;
	//用途
	private String use;
	private double monthBack;
	//紧急联系人
	private String urgencyPerson;
	private String urgencyPhone;
	//个人资料 用;隔开
	private String datums;
	//收入证明用;隔开
	private String assetCertificates;
	//征信报告;隔开
	private String creditFile;
	//个人资料
	//图片信息，只用于接收，不保存数据库表中
	private String img_1;
	private String img_2;
	private String img_3;
	private String img_4;
	private String img_5;
	private String img_6;
	private String img_7;
	private String img_8;
	private String img_9;
	private int num;
	@Transient
	public String getImg_1() {
		return img_1;
	}

	public void setImg_1(String img_1) {
		this.img_1 = img_1;
	}
	@Transient
	public String getImg_2() {
		return img_2;
	}

	public void setImg_2(String img_2) {
		this.img_2 = img_2;
	}
	@Transient
	public String getImg_3() {
		return img_3;
	}

	public void setImg_3(String img_3) {
		this.img_3 = img_3;
	}
	@Transient
	public String getImg_4() {
		return img_4;
	}

	public void setImg_4(String img_4) {
		this.img_4 = img_4;
	}
	@Transient
	public String getImg_5() {
		return img_5;
	}

	public void setImg_5(String img_5) {
		this.img_5 = img_5;
	}
	@Transient
	public String getImg_6() {
		return img_6;
	}

	public void setImg_6(String img_6) {
		this.img_6 = img_6;
	}
	@Transient
	public String getImg_7() {
		return img_7;
	}

	public void setImg_7(String img_7) {
		this.img_7 = img_7;
	}
	@Transient
	public String getImg_8() {
		return img_8;
	}

	public void setImg_8(String img_8) {
		this.img_8 = img_8;
	}
	@Transient
	public String getImg_9() {
		return img_9;
	}

	public void setImg_9(String img_9) {
		this.img_9 = img_9;
	}
	@Transient
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	/** default constructor */
	public ApplyLoanInfo() {
	}

	/** minimal constructor */
	public ApplyLoanInfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public ApplyLoanInfo(String id, LoanList loanList, double money,
			double timeLimit, String sex, String name, String phone,
			String idcard, String email, String nativeAddress,
			String nativeInfo, String address, String liveStyle,
			String education, String marriage, String house, String hasCredit,
			String creditCard, String industry, String jobStyle, String job,
			String jobCity, String company, String publicTel,
			String salaryFromBank, String yearIncome, String use,
			double monthBack, String urgencyPerson, String urgencyPhone,
			String datums, String assetCertificates) {
		this.id = id;
		this.loanList = loanList;
		this.money = money;
		this.timeLimit = timeLimit;
		this.sex = sex;
		this.name = name;
		this.phone = phone;
		this.idcard = idcard;
		this.email = email;
		this.nativeAddress = nativeAddress;
		this.nativeInfo = nativeInfo;
		this.address = address;
		this.liveStyle = liveStyle;
		this.education = education;
		this.marriage = marriage;
		this.house = house;
		this.hasCredit = hasCredit;
		this.creditCard = creditCard;
		this.industry = industry;
		this.jobStyle = jobStyle;
		this.job = job;
		this.jobCity = jobCity;
		this.company = company;
		this.publicTel = publicTel;
		this.salaryFromBank = salaryFromBank;
		this.yearIncome = yearIncome;
		this.use = use;
		this.monthBack = monthBack;
		this.urgencyPerson = urgencyPerson;
		this.urgencyPhone = urgencyPhone;
		this.datums = datums;
		this.assetCertificates = assetCertificates;
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

	@Column(name = "money", precision = 22, scale = 0)
	public double getMoney() {
		return this.money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Column(name = "time_limit", precision = 22, scale = 0)
	public double getTimeLimit() {
		return this.timeLimit;
	}

	public void setTimeLimit(double timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Column(name = "sex", length = 12)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "phone", length = 15)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "idcard", length = 20)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "native_address", length = 50)
	public String getNativeAddress() {
		return this.nativeAddress;
	}

	public void setNativeAddress(String nativeAddress) {
		this.nativeAddress = nativeAddress;
	}

	@Column(name = "native_info", length = 300)
	public String getNativeInfo() {
		return this.nativeInfo;
	}

	public void setNativeInfo(String nativeInfo) {
		this.nativeInfo = nativeInfo;
	}

	@Column(name = "address", length = 300)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "live_style", length = 30)
	public String getLiveStyle() {
		return this.liveStyle;
	}

	public void setLiveStyle(String liveStyle) {
		this.liveStyle = liveStyle;
	}

	@Column(name = "education", length = 30)
	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "marriage", length = 30)
	public String getMarriage() {
		return this.marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	@Column(name = "house", length = 300)
	public String getHouse() {
		return this.house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	@Column(name = "has_credit", length = 6)
	public String getHasCredit() {
		return this.hasCredit;
	}

	public void setHasCredit(String hasCredit) {
		this.hasCredit = hasCredit;
	}

	@Column(name = "credit_card", length = 25)
	public String getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	@Column(name = "industry", length = 300)
	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Column(name = "job_style", length = 120)
	public String getJobStyle() {
		return this.jobStyle;
	}

	public void setJobStyle(String jobStyle) {
		this.jobStyle = jobStyle;
	}

	@Column(name = "job", length = 60)
	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Column(name = "job_city", length = 30)
	public String getJobCity() {
		return this.jobCity;
	}

	public void setJobCity(String jobCity) {
		this.jobCity = jobCity;
	}

	@Column(name = "company", length = 120)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "public_tel", length = 15)
	public String getPublicTel() {
		return this.publicTel;
	}

	public void setPublicTel(String publicTel) {
		this.publicTel = publicTel;
	}

	@Column(name = "salary_from_bank", length = 6)
	public String getSalaryFromBank() {
		return this.salaryFromBank;
	}

	public void setSalaryFromBank(String salaryFromBank) {
		this.salaryFromBank = salaryFromBank;
	}

	@Column(name = "year_income", length = 50)
	public String getYearIncome() {
		return this.yearIncome;
	}

	public void setYearIncome(String yearIncome) {
		this.yearIncome = yearIncome;
	}

	@Column(name = "use", length = 3000)
	public String getUse() {
		return this.use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	@Column(name = "month_back", precision = 22, scale = 0)
	public double getMonthBack() {
		return this.monthBack;
	}

	public void setMonthBack(double monthBack) {
		this.monthBack = monthBack;
	}

	@Column(name = "urgency_person", length = 30)
	public String getUrgencyPerson() {
		return this.urgencyPerson;
	}

	public void setUrgencyPerson(String urgencyPerson) {
		this.urgencyPerson = urgencyPerson;
	}

	@Column(name = "urgency_phone", length = 15)
	public String getUrgencyPhone() {
		return this.urgencyPhone;
	}

	public void setUrgencyPhone(String urgencyPhone) {
		this.urgencyPhone = urgencyPhone;
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
	
	@Column(name = "credit_file", length = 16777215)
	public String getCreditFile() {
		return this.creditFile;
	}

	public void setCreditFile(String creditFile) {
		this.creditFile = creditFile;
	}
}