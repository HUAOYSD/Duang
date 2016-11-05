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
@SuppressWarnings("serial")
@Entity
@Table(name = "invest_list", catalog = "duang")
@DynamicInsert(true)
public class InvestList implements java.io.Serializable {

	// Fields
	private String id;
	private Scale scale;
	private InvestTicket investTicket;
	private MemberInfo memberInfo;
	private double money;
	private double backIncome;
	private double backMoney;
	private int useTicket;
	private int isTurn;
	private int turnStatus;
	private double totalMoney;
	private double income;
	private double ticketBonus;
	private int status;
	private Date openDate;
	private Date backDate;
	private Date calcBeginDate;
	private Date calcEndDate;
	private String pactNumber;
	private int investStyle;
	private double poundageTurn;
	private double poundagePrivilege;
	//红包
	private double giftSum;
	private int  days;
	private Set<Stock> stocksForInvestListId = new HashSet<Stock>(0);
	private Set<MemberTicketRecord> memberTicketRecords = new HashSet<MemberTicketRecord>(0);
	private Set<BillInvest> billInvests = new HashSet<BillInvest>(0);
	private Set<Stock> stocksForTurnInvestListId = new HashSet<Stock>(0);
	private Set<Scale> scales = new HashSet<Scale>(0);

	
	
	public InvestList(String id, Scale scale, InvestTicket investTicket,
			MemberInfo memberInfo, double money, double backIncome,
			double backMoney, int useTicket, int isTurn, int turnStatus,
			double totalMoney, double income, double ticketBonus, int status,
			Date openDate, Date backDate, Date calcBeginDate, Date calcEndDate,
			String pactNumber, int investStyle, double poundageTurn,
			double poundagePrivilege, int days,
			Set<Stock> stocksForInvestListId,
			Set<MemberTicketRecord> memberTicketRecords,
			Set<BillInvest> billInvests, Set<Stock> stocksForTurnInvestListId,
			Set<Scale> scales) {
		super();
		this.id = id;
		this.scale = scale;
		this.investTicket = investTicket;
		this.memberInfo = memberInfo;
		this.money = money;
		this.backIncome = backIncome;
		this.backMoney = backMoney;
		this.useTicket = useTicket;
		this.isTurn = isTurn;
		this.turnStatus = turnStatus;
		this.totalMoney = totalMoney;
		this.income = income;
		this.ticketBonus = ticketBonus;
		this.status = status;
		this.openDate = openDate;
		this.backDate = backDate;
		this.calcBeginDate = calcBeginDate;
		this.calcEndDate = calcEndDate;
		this.pactNumber = pactNumber;
		this.investStyle = investStyle;
		this.poundageTurn = poundageTurn;
		this.poundagePrivilege = poundagePrivilege;
		this.days = days;
		this.stocksForInvestListId = stocksForInvestListId;
		this.memberTicketRecords = memberTicketRecords;
		this.billInvests = billInvests;
		this.stocksForTurnInvestListId = stocksForTurnInvestListId;
		this.scales = scales;
	}

	// Constructors

	/** default constructor */
	public InvestList() {
	}

	/** minimal constructor */
	public InvestList(String id) {
		this.id = id;
	}

	/** full constructor */
	public InvestList(String id,Date calcBeginDate,Date calcEndDate,int isTurn,int turnStatus, Scale scale, InvestTicket investTicket,int  days, 
			MemberInfo memberInfo, double money,  double backIncome, double backMoney, int useTicket, double totalMoney, double income, double ticketBonus, int status, Date openDate, Date backDate, String pactNumber, int investStyle, double poundageTurn, double poundagePrivilege, Set<Stock> stocksForInvestListId, Set<MemberTicketRecord> memberTicketRecords, Set<BillInvest> billInvests, Set<Stock> stocksForTurnInvestListId, Set<Scale> scales) {
		this.id = id;
		this.calcBeginDate = calcBeginDate;
		this.calcEndDate = calcEndDate;
		this.turnStatus = turnStatus;
		this.isTurn = isTurn;
		this.scale = scale;
		this.investTicket = investTicket;
		this.days = days;
		this.memberInfo = memberInfo;
		this.money = money;
		this.backIncome = backIncome;
		this.backMoney = backMoney;
		this.useTicket = useTicket;
		this.totalMoney = totalMoney;
		this.income = income;
		this.ticketBonus = ticketBonus;
		this.status = status;
		this.openDate = openDate;
		this.backDate = backDate;
		this.pactNumber = pactNumber;
		this.investStyle = investStyle;
		this.poundageTurn = poundageTurn;
		this.poundagePrivilege = poundagePrivilege;
		this.stocksForInvestListId = stocksForInvestListId;
		this.memberTicketRecords = memberTicketRecords;
		this.billInvests = billInvests;
		this.stocksForTurnInvestListId = stocksForTurnInvestListId;
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
	@JoinColumn(name = "member_info")
	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	@Column(name = "money", precision = 22, scale = 0)
	public double getMoney() {
		return this.money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Column(name = "giftSum", precision = 22, scale = 0)
	public double getGiftSum() {
		return this.giftSum;
	}

	public void setGiftSum(double giftSum) {
		this.giftSum = giftSum;
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
	
	@Column(name = "turn_status")
	public int getTurnStatus() {
		return turnStatus;
	}

	public void setTurnStatus(int turnStatus) {
		this.turnStatus = turnStatus;
	}

	@Column(name = "is_turn")
	public int getIsTurn() {
		return isTurn;
	}

	public void setIsTurn(int isTurn) {
		this.isTurn = isTurn;
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

	
	
	@Column(name = "calc_beginDate", length = 19)
	public Date getCalcBeginDate() {
		return calcBeginDate;
	}

	public void setCalcBeginDate(Date calcBeginDate) {
		this.calcBeginDate = calcBeginDate;
	}
	
	@Column(name = "calc_endDate", length = 19)
	public Date getCalcEndDate() {
		return calcEndDate;
	}

	public void setCalcEndDate(Date calcEndDate) {
		this.calcEndDate = calcEndDate;
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

	@Column(name = "days")
	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	@Column(name = "poundage_turn", precision = 22, scale = 0)
	public double getPoundageTurn() {
		return this.poundageTurn;
	}

	public void setPoundageTurn(double poundageTurn) {
		this.poundageTurn = poundageTurn;
	}

	@Column(name = "poundage_privilege", precision = 22, scale = 0)
	public double getPoundagePrivilege() {
		return this.poundagePrivilege;
	}

	public void setPoundagePrivilege(double poundagePrivilege) {
		this.poundagePrivilege = poundagePrivilege;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "investList")
	public Set<Stock> getStocksForInvestListId() {
		return this.stocksForInvestListId;
	}

	public void setStocksForInvestListId(Set<Stock> stocksForInvestListId) {
		this.stocksForInvestListId = stocksForInvestListId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "investList")
	public Set<MemberTicketRecord> getMemberTicketRecords() {
		return this.memberTicketRecords;
	}

	public void setMemberTicketRecords(Set<MemberTicketRecord> memberTicketRecords) {
		this.memberTicketRecords = memberTicketRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "investList")
	public Set<BillInvest> getBillInvests() {
		return this.billInvests;
	}

	public void setBillInvests(Set<BillInvest> billInvests) {
		this.billInvests = billInvests;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "investListByTurnInvestListId")
	public Set<Stock> getStocksForTurnInvestListId() {
		return this.stocksForTurnInvestListId;
	}

	public void setStocksForTurnInvestListId(Set<Stock> stocksForTurnInvestListId) {
		this.stocksForTurnInvestListId = stocksForTurnInvestListId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "investList")
	public Set<Scale> getScales() {
		return this.scales;
	}

	public void setScales(Set<Scale> scales) {
		this.scales = scales;
	}

}