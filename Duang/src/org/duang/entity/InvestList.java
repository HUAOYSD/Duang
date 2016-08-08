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
 * InvestList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "invest_list", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class InvestList implements java.io.Serializable {

	// Fields

	private String id;
	private Scale scale;
	private InvestTicket investTicket;
	private InvestMember investMember;
	private double money;
	private double yetMoney;
	private double spaceMoney;
	private double backIncome;
	private double backMoney;
	private int useTicket;
	private double expectIncome;
	private double totalMoney;
	private double income;
	private double ticketBonus;
	private int status;
	private Date openDate;
	private Date backDate;
	private String pactNumber;
	private int investStyle;
	private Date endPayTime;
	private Set<TurnList> turnLists = new HashSet<TurnList>(0);
	private Set<MemberTicketRecord> memberTicketRecords = new HashSet<MemberTicketRecord>(
			0);
	private Set<BillInvest> billInvests = new HashSet<BillInvest>(0);
	private Set<Scale> scales = new HashSet<Scale>(0);

	// Constructors

	/** default constructor */
	public InvestList() {
	}

	/** minimal constructor */
	public InvestList(String id) {
		this.id = id;
	}

	/** full constructor */
	public InvestList(String id, Scale scale, InvestTicket investTicket,
			InvestMember investMember, double money, double yetMoney,
			double spaceMoney, double backIncome, double backMoney,
			int useTicket, double expectIncome, double totalMoney,
			double income, double ticketBonus, int status,
			Date openDate, Date backDate, String pactNumber,
			int investStyle, Date endPayTime, Set<TurnList> turnLists,
			Set<MemberTicketRecord> memberTicketRecords,
			Set<BillInvest> billInvests, Set<Scale> scales) {
		this.id = id;
		this.scale = scale;
		this.investTicket = investTicket;
		this.investMember = investMember;
		this.money = money;
		this.yetMoney = yetMoney;
		this.spaceMoney = spaceMoney;
		this.backIncome = backIncome;
		this.backMoney = backMoney;
		this.useTicket = useTicket;
		this.expectIncome = expectIncome;
		this.totalMoney = totalMoney;
		this.income = income;
		this.ticketBonus = ticketBonus;
		this.status = status;
		this.openDate = openDate;
		this.backDate = backDate;
		this.pactNumber = pactNumber;
		this.investStyle = investStyle;
		this.endPayTime = endPayTime;
		this.turnLists = turnLists;
		this.memberTicketRecords = memberTicketRecords;
		this.billInvests = billInvests;
		this.scales = scales;
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
	@JoinColumn(name = "scale_id")
	public Scale getScale() {
		return this.scale;
	}

	public void setScale(Scale scale) {
		this.scale = scale;
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

	@Column(name = "money", precision = 22, scale = 0)
	public double getMoney() {
		return this.money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Column(name = "yet_money", precision = 22, scale = 0)
	public double getYetMoney() {
		return this.yetMoney;
	}

	public void setYetMoney(double yetMoney) {
		this.yetMoney = yetMoney;
	}

	@Column(name = "space_money", precision = 22, scale = 0)
	public double getSpaceMoney() {
		return this.spaceMoney;
	}

	public void setSpaceMoney(double spaceMoney) {
		this.spaceMoney = spaceMoney;
	}

	@Column(name = "back_income", precision = 22, scale = 0)
	public double getBackIncome() {
		return this.backIncome;
	}

	public void setBackIncome(double backIncome) {
		this.backIncome = backIncome;
	}

	@Column(name = "back_money", precision = 22, scale = 0)
	public double getBackMoney() {
		return this.backMoney;
	}

	public void setBackMoney(double backMoney) {
		this.backMoney = backMoney;
	}

	@Column(name = "use_ticket")
	public int getUseTicket() {
		return this.useTicket;
	}

	public void setUseTicket(int useTicket) {
		this.useTicket = useTicket;
	}

	@Column(name = "expect_income", precision = 22, scale = 0)
	public double getExpectIncome() {
		return this.expectIncome;
	}

	public void setExpectIncome(double expectIncome) {
		this.expectIncome = expectIncome;
	}

	@Column(name = "total_money", precision = 22, scale = 0)
	public double getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Column(name = "income", precision = 22, scale = 0)
	public double getIncome() {
		return this.income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	@Column(name = "ticket_bonus", precision = 22, scale = 0)
	public double getTicketBonus() {
		return this.ticketBonus;
	}

	public void setTicketBonus(double ticketBonus) {
		this.ticketBonus = ticketBonus;
	}

	@Column(name = "status")
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "open_date", length = 19)
	public Date getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	@Column(name = "back_date", length = 19)
	public Date getBackDate() {
		return this.backDate;
	}

	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}

	@Column(name = "pact_number", length = 50)
	public String getPactNumber() {
		return this.pactNumber;
	}

	public void setPactNumber(String pactNumber) {
		this.pactNumber = pactNumber;
	}

	@Column(name = "invest_style")
	public int getInvestStyle() {
		return this.investStyle;
	}

	public void setInvestStyle(int investStyle) {
		this.investStyle = investStyle;
	}

	@Column(name = "end_pay_time", length = 19)
	public Date getEndPayTime() {
		return this.endPayTime;
	}

	public void setEndPayTime(Date endPayTime) {
		this.endPayTime = endPayTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "investList")
	public Set<TurnList> getTurnLists() {
		return this.turnLists;
	}

	public void setTurnLists(Set<TurnList> turnLists) {
		this.turnLists = turnLists;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "investList")
	public Set<MemberTicketRecord> getMemberTicketRecords() {
		return this.memberTicketRecords;
	}

	public void setMemberTicketRecords(
			Set<MemberTicketRecord> memberTicketRecords) {
		this.memberTicketRecords = memberTicketRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "investList")
	public Set<BillInvest> getBillInvests() {
		return this.billInvests;
	}

	public void setBillInvests(Set<BillInvest> billInvests) {
		this.billInvests = billInvests;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "investList")
	public Set<Scale> getScales() {
		return this.scales;
	}

	public void setScales(Set<Scale> scales) {
		this.scales = scales;
	}

}