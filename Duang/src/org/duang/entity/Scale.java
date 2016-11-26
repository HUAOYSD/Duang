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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "scale", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class Scale implements java.io.Serializable {

	// Fields

	private String id;
	private InvestList investList;
	private Product product;
	private Date beginTime;
	private Date endTime;
	private String name;
	private String timeLimit;
	private Date calcBeginTime;
	private Date calcEndTime;
	private double revenue;
	private double revenueAdd;
	private double maxLimit;
	private int returnStyle;
	private String tags;
	private int useTicket;
	private int transfer;
	private Date turnDate;
	private double totalMoney;
	private double residueMoney;
	private double yetMoney;
	private int scoreBonus;
	private int onesScore;
	private int status;
	private int isTurn;
	private String giftFlag;
	
	private String singleOrSet;
	private double minLimit;
	private String standby1;
	private double standby2;
	private int standby3;
	private Set<Stock> stocks = new HashSet<Stock>(0);
	private Set<ScaleLoanList> scaleLoanLists = new HashSet<ScaleLoanList>(0);
	private Set<InvestList> investLists = new HashSet<InvestList>(0);

	// Constructors

	/** default constructor */
	public Scale() {
	}

	/** minimal constructor */
	public Scale(String id) {
		this.id = id;
	}

	/** full constructor */
	public Scale(String id, InvestList investList, Product product,String name, Date beginTime, Date endTime, String timeLimit, 
			Date calcBeginTime, Date calcEndTime, double revenue, double revenueAdd, double maxLimit, int returnStyle, String tags, 
			int useTicket, int transfer, Date turnDate, double totalMoney, double residueMoney, double yetMoney, int scoreBonus, 
			int onesScore, int status, int isTurn,String singleOrSet,double minLimit,String standby1,double standby2,int standby3,
			Set<Stock> stocks, Set<ScaleLoanList> scaleLoanLists, Set<InvestList> investLists) {
		this.id = id;
		this.investList = investList;
		this.product = product;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.timeLimit = timeLimit;
		this.calcBeginTime = calcBeginTime;
		this.calcEndTime = calcEndTime;
		this.name = name;
		this.revenue = revenue;
		this.revenueAdd = revenueAdd;
		this.maxLimit = maxLimit;
		this.returnStyle = returnStyle;
		this.tags = tags;
		this.useTicket = useTicket;
		this.transfer = transfer;
		this.turnDate = turnDate;
		this.totalMoney = totalMoney;
		this.residueMoney = residueMoney;
		this.yetMoney = yetMoney;
		this.scoreBonus = scoreBonus;
		this.onesScore = onesScore;
		this.status = status;
		this.isTurn = isTurn;
		this.stocks = stocks;
		this.singleOrSet = singleOrSet;
		this.minLimit = minLimit;
		this.standby1 = standby1;
		this.standby2 = standby2;
		this.standby3 = standby3;
		this.scaleLoanLists = scaleLoanLists;
		this.investLists = investLists;
	}

	@Column(name = "name", length = 300)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "giftFlag", length = 10)
	public String getGiftFlag() {
		return giftFlag;
	}

	public void setGiftFlag(String giftFlag) {
		this.giftFlag = giftFlag;
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
	@JoinColumn(name = "invest_list")
	public InvestList getInvestList() {
		return this.investList;
	}

	public void setInvestList(InvestList investList) {
		this.investList = investList;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "begin_time", length = 10)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_time", length = 10)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "time_limit", length = 50)
	public String getTimeLimit() {
		return this.timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "calc_begin_time", length = 10)
	public Date getCalcBeginTime() {
		return this.calcBeginTime;
	}

	public void setCalcBeginTime(Date calcBeginTime) {
		this.calcBeginTime = calcBeginTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "calc_end_time", length = 10)
	public Date getCalcEndTime() {
		return this.calcEndTime;
	}

	public void setCalcEndTime(Date calcEndTime) {
		this.calcEndTime = calcEndTime;
	}

	@Column(name = "revenue", precision = 22, scale = 0)
	public double getRevenue() {
		return this.revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	@Column(name = "revenue_add", precision = 22, scale = 0)
	public double getRevenueAdd() {
		return this.revenueAdd;
	}

	public void setRevenueAdd(double revenueAdd) {
		this.revenueAdd = revenueAdd;
	}

	@Column(name = "max_limit", precision = 22, scale = 0)
	public double getMaxLimit() {
		return this.maxLimit;
	}

	public void setMaxLimit(double maxLimit) {
		this.maxLimit = maxLimit;
	}

	@Column(name = "return_style")
	public int getReturnStyle() {
		return this.returnStyle;
	}

	public void setReturnStyle(int returnStyle) {
		this.returnStyle = returnStyle;
	}

	@Column(name = "tags", length = 2000)
	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Column(name = "use_ticket")
	public int getUseTicket() {
		return this.useTicket;
	}

	public void setUseTicket(int useTicket) {
		this.useTicket = useTicket;
	}

	@Column(name = "transfer")
	public int getTransfer() {
		return this.transfer;
	}

	public void setTransfer(int transfer) {
		this.transfer = transfer;
	}

	@Column(name = "turn_date", length = 19)
	public Date getTurnDate() {
		return this.turnDate;
	}

	public void setTurnDate(Date turnDate) {
		this.turnDate = turnDate;
	}

	@Column(name = "total_money", precision = 22, scale = 0)
	public double getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Column(name = "residue_money", precision = 22, scale = 0)
	public double getResidueMoney() {
		return this.residueMoney;
	}

	public void setResidueMoney(double residueMoney) {
		this.residueMoney = residueMoney;
	}

	@Column(name = "yet_money", precision = 22, scale = 0)
	public double getYetMoney() {
		return this.yetMoney;
	}

	public void setYetMoney(double yetMoney) {
		this.yetMoney = yetMoney;
	}

	@Column(name = "score_bonus")
	public int getScoreBonus() {
		return this.scoreBonus;
	}

	public void setScoreBonus(int scoreBonus) {
		this.scoreBonus = scoreBonus;
	}

	@Column(name = "ones_score")
	public int getOnesScore() {
		return this.onesScore;
	}

	public void setOnesScore(int onesScore) {
		this.onesScore = onesScore;
	}

	@Column(name = "status")
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "is_turn")
	public int getIsTurn() {
		return this.isTurn;
	}

	public void setIsTurn(int isTurn) {
		this.isTurn = isTurn;
	}
	
	@Column(name = "singleOrSet")
	public String getSingleOrSet() {
		return singleOrSet;
	}

	public void setSingleOrSet(String singleOrSet) {
		this.singleOrSet = singleOrSet;
	}

	@Column(name = "min_limit")
	public double getMinLimit() {
		return minLimit;
	}

	public void setMinLimit(double minLimit) {
		this.minLimit = minLimit;
	}

	@Column(name = "standby1")
	public String getStandby1() {
		return standby1;
	}

	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}

	@Column(name = "standby2")
	public double getStandby2() {
		return standby2;
	}

	public void setStandby2(double standby2) {
		this.standby2 = standby2;
	}

	@Column(name = "standby3")
	public int getStandby3() {
		return standby3;
	}

	public void setStandby3(int standby3) {
		this.standby3 = standby3;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "scale")
	public Set<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "scale")
	public Set<ScaleLoanList> getScaleLoanLists() {
		return this.scaleLoanLists;
	}

	public void setScaleLoanLists(Set<ScaleLoanList> scaleLoanLists) {
		this.scaleLoanLists = scaleLoanLists;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "scale")
	public Set<InvestList> getInvestLists() {
		return this.investLists;
	}

	public void setInvestLists(Set<InvestList> investLists) {
		this.investLists = investLists;
	}

}