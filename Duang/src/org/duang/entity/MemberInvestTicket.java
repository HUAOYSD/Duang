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
 * MemberInvestTicket entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "member_invest_ticket", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class MemberInvestTicket implements java.io.Serializable {

	// Fields

	private String id;
	private InvestTicket investTicket;
	private InvestMember investMember;
	private String resourceStyle;
	private int isUse;

	// Constructors

	/** default constructor */
	public MemberInvestTicket() {
	}

	/** minimal constructor */
	public MemberInvestTicket(String id) {
		this.id = id;
	}

	/** full constructor */
	public MemberInvestTicket(String id, InvestTicket investTicket,
			InvestMember investMember, String resourceStyle, int isUse) {
		this.id = id;
		this.investTicket = investTicket;
		this.investMember = investMember;
		this.resourceStyle = resourceStyle;
		this.isUse = isUse;
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
	@JoinColumn(name = "invest_ticket_id")
	public InvestTicket getInvestTicket() {
		return this.investTicket;
	}

	public void setInvestTicket(InvestTicket investTicket) {
		this.investTicket = investTicket;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invest_member_id")
	public InvestMember getInvestMember() {
		return this.investMember;
	}

	public void setInvestMember(InvestMember investMember) {
		this.investMember = investMember;
	}

	@Column(name = "resource_style", length = 500)
	public String getResourceStyle() {
		return this.resourceStyle;
	}

	public void setResourceStyle(String resourceStyle) {
		this.resourceStyle = resourceStyle;
	}

	@Column(name = "is_use")
	public int getIsUse() {
		return this.isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

}