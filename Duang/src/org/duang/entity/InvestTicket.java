package org.duang.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

/**
 * InvestTicket entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "invest_ticket", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class InvestTicket implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String remark;
	private String describes;
	private double money;
	private Date beginTime;
	private Date endTime;
	private Date createTime;
	private double minMoney;
	private String productIds;
	private int state;
	private Set<InvestList> investLists = new HashSet<InvestList>(0);
	private Set<MemberTicketRecord> memberTicketRecords = new HashSet<MemberTicketRecord>(
			0);
	private Set<MemberInvestTicket> memberInvestTickets = new HashSet<MemberInvestTicket>(
			0);

	// Constructors

	/** default constructor */
	public InvestTicket() {
	}

	/** minimal constructor */
	public InvestTicket(String id) {
		this.id = id;
	}

	/** full constructor */
	public InvestTicket(String id, String name, String remark, String describes,
			double money, Date beginTime, Date endTime,
			Date createTime, double minMoney, String productIds, int state,
			Set<InvestList> investLists,
			Set<MemberTicketRecord> memberTicketRecords,
			Set<MemberInvestTicket> memberInvestTickets) {
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.describes = describes;
		this.money = money;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.createTime = createTime;
		this.minMoney = minMoney;
		this.productIds = productIds;
		this.investLists = investLists;
		this.state = state;
		this.memberTicketRecords = memberTicketRecords;
		this.memberInvestTickets = memberInvestTickets;
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

	@Column(name = "name", length = 500)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "remark", length = 2000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "describes", length = 16777215)
	public String getDescribes() {
		return this.describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	@Column(name = "money", precision = 22, scale = 0)
	public double getMoney() {
		return this.money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Column(name = "begin_time", length = 19)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "end_time", length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "min_money", precision = 22, scale = 0)
	public double getMinMoney() {
		return this.minMoney;
	}

	public void setMinMoney(double minMoney) {
		this.minMoney = minMoney;
	}

	@Column(name = "product_ids", length = 3000)
	public String getProductIds() {
		return this.productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}
	@Column(name = "state", length = 11)
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "investTicket")
	public Set<InvestList> getInvestLists() {
		return this.investLists;
	}

	public void setInvestLists(Set<InvestList> investLists) {
		this.investLists = investLists;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "investTicket")
	public Set<MemberTicketRecord> getMemberTicketRecords() {
		return this.memberTicketRecords;
	}

	public void setMemberTicketRecords(
			Set<MemberTicketRecord> memberTicketRecords) {
		this.memberTicketRecords = memberTicketRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "investTicket")
	public Set<MemberInvestTicket> getMemberInvestTickets() {
		return this.memberInvestTickets;
	}

	public void setMemberInvestTickets(
			Set<MemberInvestTicket> memberInvestTickets) {
		this.memberInvestTickets = memberInvestTickets;
	}

}