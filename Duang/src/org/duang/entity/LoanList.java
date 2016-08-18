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
 * LoanList entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "loan_list", catalog = "duang")
@DynamicInsert(true)
public class LoanList implements java.io.Serializable {

	// Fields

	private String id;
	private LoanMember loanMember;
	private int loanType;
	private String pactNumber;
	private int isSell;
	private int poundageState;
	private double money;
	private double realMoney;
	private double manageCost;
	private double poundage;
	private double getMoney;
	private double yetMoney;
	private double returnMoney;
	private double agoMoney;
	private double yetReturnMoney;
	private int returnStatus;
	private int loanState;
	private int applyState;
	private String loanUse;
	private double loanInterest;
	private Date createTime;
	private Date signDate;
	private Date beginReturnDate;
	private Date endReturnDate;
	private Date doneReturnDate;
	private int loanStyle;
	private int backStyle;
	private CustomerManager customerManager;
	private Set<Stock> stocks = new HashSet<Stock>(0);
	private Set<ApplyLoanResult> applyLoanResults = new HashSet<ApplyLoanResult>(0);
	private Set<ScaleLoanList> scaleLoanLists = new HashSet<ScaleLoanList>(0);
	private Set<BillLoan> billLoans = new HashSet<BillLoan>(0);
	private Set<ApplyLoanInfo> applyLoanInfos = new HashSet<ApplyLoanInfo>(0);
	private Set<ApplyLoanHouse> applyLoanHouses = new HashSet<ApplyLoanHouse>(0);
	private Set<ApplyLoanCar> applyLoanCars = new HashSet<ApplyLoanCar>(0);

	// Constructors

	/** default constructor */
	public LoanList() {
	}

	/** minimal constructor */
	public LoanList(String id, LoanMember loanMember) {
		this.id = id;
		this.loanMember = loanMember;
	}
	
	public LoanList(String id) {
		this.id = id;
	}

	/** full constructor */
	public LoanList(String id, LoanMember loanMember, int loanType, String pactNumber, int isSell, int poundageState, double money, double realMoney, double manageCost, double poundage, double getMoney, double yetMoney, double returnMoney, double agoMoney, double yetReturnMoney, int returnStatus, int loanState, int applyState, String loanUse, double loanInterest, Date createTime, Date signDate, Date beginReturnDate, Date endReturnDate, Date doneReturnDate, int loanStyle, int backStyle, CustomerManager customerManager,Set<Stock> stocks, Set<ApplyLoanResult> applyLoanResults, Set<ScaleLoanList> scaleLoanLists, Set<BillLoan> billLoans, Set<ApplyLoanInfo> applyLoanInfos, Set<ApplyLoanHouse> applyLoanHouses, Set<ApplyLoanCar> applyLoanCars) {
		this.id = id;
		this.loanMember = loanMember;
		this.loanType = loanType;
		this.pactNumber = pactNumber;
		this.isSell = isSell;
		this.poundageState = poundageState;
		this.money = money;
		this.realMoney = realMoney;
		this.manageCost = manageCost;
		this.poundage = poundage;
		this.getMoney = getMoney;
		this.yetMoney = yetMoney;
		this.returnMoney = returnMoney;
		this.agoMoney = agoMoney;
		this.yetReturnMoney = yetReturnMoney;
		this.returnStatus = returnStatus;
		this.loanState = loanState;
		this.applyState = applyState;
		this.loanUse = loanUse;
		this.loanInterest = loanInterest;
		this.createTime = createTime;
		this.signDate = signDate;
		this.beginReturnDate = beginReturnDate;
		this.endReturnDate = endReturnDate;
		this.doneReturnDate = doneReturnDate;
		this.loanStyle = loanStyle;
		this.backStyle = backStyle;
		this.stocks = stocks;
		this.applyLoanResults = applyLoanResults;
		this.scaleLoanLists = scaleLoanLists;
		this.billLoans = billLoans;
		this.applyLoanInfos = applyLoanInfos;
		this.applyLoanHouses = applyLoanHouses;
		this.applyLoanCars = applyLoanCars;
		this.customerManager = customerManager;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	public CustomerManager getCustomerManager() {
		return customerManager;
	}

	public void setCustomerManager(CustomerManager customerManager) {
		this.customerManager = customerManager;
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
	@JoinColumn(name = "loan_member_id", nullable = false)
	public LoanMember getLoanMember() {
		return this.loanMember;
	}

	public void setLoanMember(LoanMember loanMember) {
		this.loanMember = loanMember;
	}

	@Column(name = "loan_type")
	public int getLoanType() {
		return this.loanType;
	}

	public void setLoanType(int loanType) {
		this.loanType = loanType;
	}

	@Column(name = "pact_number", length = 50)
	public String getPactNumber() {
		return this.pactNumber;
	}

	public void setPactNumber(String pactNumber) {
		this.pactNumber = pactNumber;
	}

	@Column(name = "is_sell")
	public int getIsSell() {
		return this.isSell;
	}

	public void setIsSell(int isSell) {
		this.isSell = isSell;
	}

	@Column(name = "poundage_state")
	public int getPoundageState() {
		return this.poundageState;
	}

	public void setPoundageState(int poundageState) {
		this.poundageState = poundageState;
	}

	@Column(name = "money", precision = 22, scale = 0)
	public double getMoney() {
		return this.money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Column(name = "real_money", precision = 22, scale = 0)
	public double getRealMoney() {
		return this.realMoney;
	}

	public void setRealMoney(double realMoney) {
		this.realMoney = realMoney;
	}

	@Column(name = "manage_cost", precision = 22, scale = 0)
	public double getManageCost() {
		return this.manageCost;
	}

	public void setManageCost(double manageCost) {
		this.manageCost = manageCost;
	}

	@Column(name = "poundage", precision = 22, scale = 0)
	public double getPoundage() {
		return this.poundage;
	}

	public void setPoundage(double poundage) {
		this.poundage = poundage;
	}

	@Column(name = "get_money", precision = 22, scale = 0)
	public double getGetMoney() {
		return this.getMoney;
	}

	public void setGetMoney(double getMoney) {
		this.getMoney = getMoney;
	}

	@Column(name = "yet_money", precision = 22, scale = 0)
	public double getYetMoney() {
		return this.yetMoney;
	}

	public void setYetMoney(double yetMoney) {
		this.yetMoney = yetMoney;
	}

	@Column(name = "return_money", precision = 22, scale = 0)
	public double getReturnMoney() {
		return this.returnMoney;
	}

	public void setReturnMoney(double returnMoney) {
		this.returnMoney = returnMoney;
	}

	@Column(name = "ago_money", precision = 22, scale = 0)
	public double getAgoMoney() {
		return this.agoMoney;
	}

	public void setAgoMoney(double agoMoney) {
		this.agoMoney = agoMoney;
	}

	@Column(name = "yet_return_money", precision = 22, scale = 0)
	public double getYetReturnMoney() {
		return this.yetReturnMoney;
	}

	public void setYetReturnMoney(double yetReturnMoney) {
		this.yetReturnMoney = yetReturnMoney;
	}

	@Column(name = "return_status")
	public int getReturnStatus() {
		return this.returnStatus;
	}

	public void setReturnStatus(int returnStatus) {
		this.returnStatus = returnStatus;
	}

	@Column(name = "loan_state")
	public int getLoanState() {
		return this.loanState;
	}

	public void setLoanState(int loanState) {
		this.loanState = loanState;
	}

	@Column(name = "apply_state")
	public int getApplyState() {
		return this.applyState;
	}

	public void setApplyState(int applyState) {
		this.applyState = applyState;
	}

	@Column(name = "loan_use")
	public String getLoanUse() {
		return this.loanUse;
	}

	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
	}

	@Column(name = "loan_interest", precision = 22, scale = 0)
	public double getLoanInterest() {
		return this.loanInterest;
	}

	public void setLoanInterest(double loanInterest) {
		this.loanInterest = loanInterest;
	}

	@Column(name = "createTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "sign_date", length = 19)
	public Date getSignDate() {
		return this.signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	@Column(name = "begin_return_date", length = 19)
	public Date getBeginReturnDate() {
		return this.beginReturnDate;
	}

	public void setBeginReturnDate(Date beginReturnDate) {
		this.beginReturnDate = beginReturnDate;
	}

	@Column(name = "end_return_date", length = 19)
	public Date getEndReturnDate() {
		return this.endReturnDate;
	}

	public void setEndReturnDate(Date endReturnDate) {
		this.endReturnDate = endReturnDate;
	}

	@Column(name = "done_return_date", length = 19)
	public Date getDoneReturnDate() {
		return this.doneReturnDate;
	}

	public void setDoneReturnDate(Date doneReturnDate) {
		this.doneReturnDate = doneReturnDate;
	}

	@Column(name = "loan_style")
	public int getLoanStyle() {
		return this.loanStyle;
	}

	public void setLoanStyle(int loanStyle) {
		this.loanStyle = loanStyle;
	}

	@Column(name = "back_style")
	public int getBackStyle() {
		return this.backStyle;
	}

	public void setBackStyle(int backStyle) {
		this.backStyle = backStyle;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "loanList")
	public Set<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "loanList")
	public Set<ApplyLoanResult> getApplyLoanResults() {
		return this.applyLoanResults;
	}

	public void setApplyLoanResults(Set<ApplyLoanResult> applyLoanResults) {
		this.applyLoanResults = applyLoanResults;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "loanList")
	public Set<ScaleLoanList> getScaleLoanLists() {
		return this.scaleLoanLists;
	}

	public void setScaleLoanLists(Set<ScaleLoanList> scaleLoanLists) {
		this.scaleLoanLists = scaleLoanLists;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "loanList")
	public Set<BillLoan> getBillLoans() {
		return this.billLoans;
	}

	public void setBillLoans(Set<BillLoan> billLoans) {
		this.billLoans = billLoans;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "loanList")
	public Set<ApplyLoanInfo> getApplyLoanInfos() {
		return this.applyLoanInfos;
	}

	public void setApplyLoanInfos(Set<ApplyLoanInfo> applyLoanInfos) {
		this.applyLoanInfos = applyLoanInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "loanList")
	public Set<ApplyLoanHouse> getApplyLoanHouses() {
		return this.applyLoanHouses;
	}

	public void setApplyLoanHouses(Set<ApplyLoanHouse> applyLoanHouses) {
		this.applyLoanHouses = applyLoanHouses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "loanList")
	public Set<ApplyLoanCar> getApplyLoanCars() {
		return this.applyLoanCars;
	}

	public void setApplyLoanCars(Set<ApplyLoanCar> applyLoanCars) {
		this.applyLoanCars = applyLoanCars;
	}

}