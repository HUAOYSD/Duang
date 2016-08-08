package org.duang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

/**
 * MemberTicketRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "member_ticket_record", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class MemberTicketRecord implements java.io.Serializable {

	// Fields

	private String id;
	private InvestTicket investTicket;
	private InvestList investList;
	private Date useTime;

	// Constructors

	/** default constructor */
	public MemberTicketRecord() {
	}

	/** minimal constructor */
	public MemberTicketRecord(String id) {
		this.id = id;
	}

	/** full constructor */
	public MemberTicketRecord(String id, InvestTicket investTicket,
			InvestList investList, Date useTime) {
		this.id = id;
		this.investTicket = investTicket;
		this.investList = investList;
		this.useTime = useTime;
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
	@JoinColumn(name = "invest_list_id")
	public InvestList getInvestList() {
		return this.investList;
	}

	public void setInvestList(InvestList investList) {
		this.investList = investList;
	}

	@Column(name = "use_time", length = 19)
	public Date getUseTime() {
		return this.useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

}