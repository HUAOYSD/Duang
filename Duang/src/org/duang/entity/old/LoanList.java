package org.duang.entity.old;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LoanList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "loan_list", catalog = "duang")
public class LoanList implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String contractId;
	private String isSell;
	private String state;
	private Float loanMoney;
	private Float handlingCharge;
	private Float shouldLoanMoney;
	private Float lastStorage;
	private Integer lastIntMoney;
	private Integer yetLoanIntMoney;
	private Integer notLoanIntMoney;
	private Integer notLoanIntStorage;
	private Float lastDecMoney;
	private Float yetLoanDecMoney;
	private Float notLoanDecMoney;
	private String loanType;
	private String loanPurpose;
	private Float loanInterest;
	private Timestamp createTime;
	private Timestamp contractDate;

	// Constructors

	/** default constructor */
	public LoanList() {
	}

	/** minimal constructor */
	public LoanList(String id, String userId, String contractId) {
		this.id = id;
		this.userId = userId;
		this.contractId = contractId;
	}

	/** full constructor */
	public LoanList(String id, String userId, String contractId, String isSell, String state, Float loanMoney, Float handlingCharge, Float shouldLoanMoney, Float lastStorage, Integer lastIntMoney, Integer yetLoanIntMoney, Integer notLoanIntMoney, Integer notLoanIntStorage, Float lastDecMoney, Float yetLoanDecMoney, Float notLoanDecMoney, String loanType, String loanPurpose, Float loanInterest, Timestamp createTime, Timestamp contractDate) {
		this.id = id;
		this.userId = userId;
		this.contractId = contractId;
		this.isSell = isSell;
		this.state = state;
		this.loanMoney = loanMoney;
		this.handlingCharge = handlingCharge;
		this.shouldLoanMoney = shouldLoanMoney;
		this.lastStorage = lastStorage;
		this.lastIntMoney = lastIntMoney;
		this.yetLoanIntMoney = yetLoanIntMoney;
		this.notLoanIntMoney = notLoanIntMoney;
		this.notLoanIntStorage = notLoanIntStorage;
		this.lastDecMoney = lastDecMoney;
		this.yetLoanDecMoney = yetLoanDecMoney;
		this.notLoanDecMoney = notLoanDecMoney;
		this.loanType = loanType;
		this.loanPurpose = loanPurpose;
		this.loanInterest = loanInterest;
		this.createTime = createTime;
		this.contractDate = contractDate;
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

	@Column(name = "contract_id", nullable = false)
	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@Column(name = "is_sell")
	public String getIsSell() {
		return this.isSell;
	}

	public void setIsSell(String isSell) {
		this.isSell = isSell;
	}

	@Column(name = "state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "loan_money", precision = 50, scale = 6)
	public Float getLoanMoney() {
		return this.loanMoney;
	}

	public void setLoanMoney(Float loanMoney) {
		this.loanMoney = loanMoney;
	}

	@Column(name = "handling_charge", precision = 50, scale = 6)
	public Float getHandlingCharge() {
		return this.handlingCharge;
	}

	public void setHandlingCharge(Float handlingCharge) {
		this.handlingCharge = handlingCharge;
	}

	@Column(name = "should_loan_money", precision = 50, scale = 6)
	public Float getShouldLoanMoney() {
		return this.shouldLoanMoney;
	}

	public void setShouldLoanMoney(Float shouldLoanMoney) {
		this.shouldLoanMoney = shouldLoanMoney;
	}

	@Column(name = "last_storage", precision = 50, scale = 6)
	public Float getLastStorage() {
		return this.lastStorage;
	}

	public void setLastStorage(Float lastStorage) {
		this.lastStorage = lastStorage;
	}

	@Column(name = "last_int_money")
	public Integer getLastIntMoney() {
		return this.lastIntMoney;
	}

	public void setLastIntMoney(Integer lastIntMoney) {
		this.lastIntMoney = lastIntMoney;
	}

	@Column(name = "yet_loan_int_money")
	public Integer getYetLoanIntMoney() {
		return this.yetLoanIntMoney;
	}

	public void setYetLoanIntMoney(Integer yetLoanIntMoney) {
		this.yetLoanIntMoney = yetLoanIntMoney;
	}

	@Column(name = "not_loan_int_money")
	public Integer getNotLoanIntMoney() {
		return this.notLoanIntMoney;
	}

	public void setNotLoanIntMoney(Integer notLoanIntMoney) {
		this.notLoanIntMoney = notLoanIntMoney;
	}

	@Column(name = "not_loan_int_storage")
	public Integer getNotLoanIntStorage() {
		return this.notLoanIntStorage;
	}

	public void setNotLoanIntStorage(Integer notLoanIntStorage) {
		this.notLoanIntStorage = notLoanIntStorage;
	}

	@Column(name = "last_dec_money", precision = 255, scale = 0)
	public Float getLastDecMoney() {
		return this.lastDecMoney;
	}

	public void setLastDecMoney(Float lastDecMoney) {
		this.lastDecMoney = lastDecMoney;
	}

	@Column(name = "yet_loan_dec_money", precision = 255, scale = 0)
	public Float getYetLoanDecMoney() {
		return this.yetLoanDecMoney;
	}

	public void setYetLoanDecMoney(Float yetLoanDecMoney) {
		this.yetLoanDecMoney = yetLoanDecMoney;
	}

	@Column(name = "not_loan_dec_money", precision = 255, scale = 0)
	public Float getNotLoanDecMoney() {
		return this.notLoanDecMoney;
	}

	public void setNotLoanDecMoney(Float notLoanDecMoney) {
		this.notLoanDecMoney = notLoanDecMoney;
	}

	@Column(name = "loan_type", length = 20)
	public String getLoanType() {
		return this.loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	@Column(name = "loan_purpose")
	public String getLoanPurpose() {
		return this.loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	@Column(name = "loan_interest", precision = 255, scale = 0)
	public Float getLoanInterest() {
		return this.loanInterest;
	}

	public void setLoanInterest(Float loanInterest) {
		this.loanInterest = loanInterest;
	}

	@Column(name = "createTime", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "contract_date", length = 19)
	public Timestamp getContractDate() {
		return this.contractDate;
	}

	public void setContractDate(Timestamp contractDate) {
		this.contractDate = contractDate;
	}

}