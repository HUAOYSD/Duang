package org.duang.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.hibernate.engine.internal.Cascade;

/**
 * InvestMember entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "invest_member", catalog = "duang")
public class InvestMember implements java.io.Serializable {

	// Fields

	private String id;
	private MemberInfo memberInfo;
	private String idcard;
	private String bankCard;
	private String bank;
	private String userImage;
	private String idcardImg1;
	private String idcardImg2;
	private String custManagerId;
	private String managerName;
	private String isContract;
	private double investMoney;
	private double investingMoney;
	private double useableMoney;
	private double accountTotalMoney;
	private double freezeMoney;
	private double unfreezeMoney;
	private int useableScore;
	private String allowOnline;

	// Constructors

	/** default constructor */
	public InvestMember() {
	}

	/** minimal constructor */
	public InvestMember(String id, MemberInfo memberInfo) {
		this.id = id;
		this.memberInfo = memberInfo;
	}

	/** full constructor */
	public InvestMember(String id, MemberInfo memberInfo, String idcard, String bankCard, String bank, String userImage, String idcardImg1, String idcardImg2, String custManagerId, String managerName, String isContract, double investMoney, double investingMoney, double useableMoney, double accountTotalMoney, double freezeMoney, double unfreezeMoney, int useableScore, String allowOnline) {
		this.id = id;
		this.memberInfo = memberInfo;
		this.idcard = idcard;
		this.bankCard = bankCard;
		this.bank = bank;
		this.userImage = userImage;
		this.idcardImg1 = idcardImg1;
		this.idcardImg2 = idcardImg2;
		this.custManagerId = custManagerId;
		this.managerName = managerName;
		this.isContract = isContract;
		this.investMoney = investMoney;
		this.investingMoney = investingMoney;
		this.useableMoney = useableMoney;
		this.accountTotalMoney = accountTotalMoney;
		this.freezeMoney = freezeMoney;
		this.unfreezeMoney = unfreezeMoney;
		this.useableScore = useableScore;
		this.allowOnline = allowOnline;
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

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "memberinfo_id", nullable = false)
	public MemberInfo getMemberInfo() {
		return this.memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	@Column(name = "idcard")
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "bank_card")
	public String getBankCard() {
		return this.bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	@Column(name = "bank")
	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "user_image")
	public String getUserImage() {
		return this.userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	@Column(name = "idcard_img1")
	public String getIdcardImg1() {
		return this.idcardImg1;
	}

	public void setIdcardImg1(String idcardImg1) {
		this.idcardImg1 = idcardImg1;
	}

	@Column(name = "idcard_img2")
	public String getIdcardImg2() {
		return this.idcardImg2;
	}

	public void setIdcardImg2(String idcardImg2) {
		this.idcardImg2 = idcardImg2;
	}

	@Column(name = "cust_manager_id")
	public String getCustManagerId() {
		return this.custManagerId;
	}

	public void setCustManagerId(String custManagerId) {
		this.custManagerId = custManagerId;
	}

	@Column(name = "manager_name")
	public String getManagerName() {
		return this.managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	@Column(name = "is_contract")
	public String getIsContract() {
		return this.isContract;
	}

	public void setIsContract(String isContract) {
		this.isContract = isContract;
	}

	@Column(name = "invest_money", precision = 25, scale = 6)
	public double getInvestMoney() {
		return this.investMoney;
	}

	public void setInvestMoney(double investMoney) {
		this.investMoney = investMoney;
	}

	@Column(name = "investing_money", precision = 25, scale = 6)
	public double getInvestingMoney() {
		return this.investingMoney;
	}

	public void setInvestingMoney(double investingMoney) {
		this.investingMoney = investingMoney;
	}

	@Column(name = "useable_money", precision = 25, scale = 6)
	public double getUseableMoney() {
		return this.useableMoney;
	}

	public void setUseableMoney(double useableMoney) {
		this.useableMoney = useableMoney;
	}

	@Column(name = "account_total_money", precision = 25, scale = 6)
	public double getAccountTotalMoney() {
		return this.accountTotalMoney;
	}

	public void setAccountTotalMoney(double accountTotalMoney) {
		this.accountTotalMoney = accountTotalMoney;
	}

	@Column(name = "freeze_money", precision = 25, scale = 6)
	public double getFreezeMoney() {
		return this.freezeMoney;
	}

	public void setFreezeMoney(double freezeMoney) {
		this.freezeMoney = freezeMoney;
	}

	@Column(name = "unfreeze_money", precision = 25, scale = 6)
	public double getUnfreezeMoney() {
		return this.unfreezeMoney;
	}

	public void setUnfreezeMoney(double unfreezeMoney) {
		this.unfreezeMoney = unfreezeMoney;
	}

	@Column(name = "useable_score")
	public int getUseableScore() {
		return this.useableScore;
	}

	public void setUseableScore(int useableScore) {
		this.useableScore = useableScore;
	}

	@Column(name = "allow_online", length = 3)
	public String getAllowOnline() {
		return this.allowOnline;
	}

	public void setAllowOnline(String allowOnline) {
		this.allowOnline = allowOnline;
	}

}