package org.duang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LoanMember entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "loan_member", catalog = "duang")
public class LoanMember implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String idcard;
	private String bankCard;
	private String bank;

	// Constructors

	/** default constructor */
	public LoanMember() {
	}

	/** minimal constructor */
	public LoanMember(String id, String userId) {
		this.id = id;
		this.userId = userId;
	}

	/** full constructor */
	public LoanMember(String id, String userId, String idcard, String bankCard, String bank) {
		this.id = id;
		this.userId = userId;
		this.idcard = idcard;
		this.bankCard = bankCard;
		this.bank = bank;
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

}