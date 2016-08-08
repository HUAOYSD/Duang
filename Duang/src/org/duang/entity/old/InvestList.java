package org.duang.entity.old;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * InvestList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "invest_list", catalog = "duang")
public class InvestList implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private Float investSumMoney;
	private Float investDoingMoney;
	private Float investUsableBalance;
	private Float accountSumBalance;
	private Float freezeBalance;
	private Float notTransferBalance;
	private Integer usableIntegral;
	private Date lastDate;
	private Integer isContractUser;
	private Date openDate;
	private Integer isOnline;

	// Constructors

	/** default constructor */
	public InvestList() {
	}

	/** minimal constructor */
	public InvestList(String id, String userId) {
		this.id = id;
		this.userId = userId;
	}

	/** full constructor */
	public InvestList(String id, String userId, Float investSumMoney, Float investDoingMoney, Float investUsableBalance, Float accountSumBalance, Float freezeBalance, Float notTransferBalance, Integer usableIntegral, Date lastDate, Integer isContractUser, Date openDate, Integer isOnline) {
		this.id = id;
		this.userId = userId;
		this.investSumMoney = investSumMoney;
		this.investDoingMoney = investDoingMoney;
		this.investUsableBalance = investUsableBalance;
		this.accountSumBalance = accountSumBalance;
		this.freezeBalance = freezeBalance;
		this.notTransferBalance = notTransferBalance;
		this.usableIntegral = usableIntegral;
		this.lastDate = lastDate;
		this.isContractUser = isContractUser;
		this.openDate = openDate;
		this.isOnline = isOnline;
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

	@Column(name = "invest_sum_money", precision = 50, scale = 6)
	public Float getInvestSumMoney() {
		return this.investSumMoney;
	}

	public void setInvestSumMoney(Float investSumMoney) {
		this.investSumMoney = investSumMoney;
	}

	@Column(name = "invest_doing_money", precision = 50, scale = 6)
	public Float getInvestDoingMoney() {
		return this.investDoingMoney;
	}

	public void setInvestDoingMoney(Float investDoingMoney) {
		this.investDoingMoney = investDoingMoney;
	}

	@Column(name = "invest_usable_balance", precision = 50, scale = 6)
	public Float getInvestUsableBalance() {
		return this.investUsableBalance;
	}

	public void setInvestUsableBalance(Float investUsableBalance) {
		this.investUsableBalance = investUsableBalance;
	}

	@Column(name = "account_sum_balance", precision = 50, scale = 6)
	public Float getAccountSumBalance() {
		return this.accountSumBalance;
	}

	public void setAccountSumBalance(Float accountSumBalance) {
		this.accountSumBalance = accountSumBalance;
	}

	@Column(name = "freeze_balance", precision = 50, scale = 6)
	public Float getFreezeBalance() {
		return this.freezeBalance;
	}

	public void setFreezeBalance(Float freezeBalance) {
		this.freezeBalance = freezeBalance;
	}

	@Column(name = "not_transfer_balance", precision = 50, scale = 6)
	public Float getNotTransferBalance() {
		return this.notTransferBalance;
	}

	public void setNotTransferBalance(Float notTransferBalance) {
		this.notTransferBalance = notTransferBalance;
	}

	@Column(name = "usable_integral")
	public Integer getUsableIntegral() {
		return this.usableIntegral;
	}

	public void setUsableIntegral(Integer usableIntegral) {
		this.usableIntegral = usableIntegral;
	}

	@Column(name = "last_date", length = 19)
	public Date getLastDate() {
		return this.lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	@Column(name = "is_contract_user")
	public Integer getIsContractUser() {
		return this.isContractUser;
	}

	public void setIsContractUser(Integer isContractUser) {
		this.isContractUser = isContractUser;
	}

	@Column(name = "open_date", length = 19)
	public Date getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	@Column(name = "is_online")
	public Integer getIsOnline() {
		return this.isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

}