package org.duang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "product", catalog = "duang")
public class Product implements java.io.Serializable {

	// Fields

	private String id;
	/**
	 * 产品类型，默认信贷产品，标类产品
	 */
	private int category;
	/**
	 * 总名称  如：年年余
	 */
	private String nameZh;
	/**
	 * 名称  6月期，12月期
	 */
	private String name;
	/**
	 * 描述
	 */
	private String nameDescribe;
	/**
	 * 收益率描述
	 */
	private String yieldDescribe;
	/**
	 * 收益率
	 */
	private Double yield;
	/**
	 * 标题1
	 */
	private String title1;
	/**
	 * 标题2
	 */
	private String title2;
	/**
	 * 起投期限
	 */
	private String minDeadline;
	/**
	 * 起投金额
	 */
	private String minMoney;
	/**
	 * 还款方式
	 */
	private String refundType;
	/**
	 * 是否起售 0：停售 1：起售
	 */
	private int isSell;
	/**
	 * 是否为新品  0:否  1：是
	 */
	private int isNewProduct;
	/**
	 * 是否推荐 0否 1是推荐
	 */
	private int isRecommend;
	/**
	 * 产品介绍
	 */
	private String productDescribe;
	/**
	 * 风险控制
	 */
	private String riskControl;
	/**
	 * 更多详情
	 */
	private String details;
	/**
	 * 删除状态 是否删除  1：已删除 0未删除
	 */
	private int isdelete;
	private Date createtime;
	private Date modifytime;
	private String createuser;
	private String modifyuser;
	private int useTicket;

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** minimal constructor */
	public Product(String id, int category, String nameZh, String name, Double yield, String minDeadline, String minMoney, int isdelete) {
		this.id = id;
		this.category = category;
		this.nameZh = nameZh;
		this.name = name;
		this.yield = yield;
		this.minDeadline = minDeadline;
		this.minMoney = minMoney;
		this.isdelete = isdelete;
	}

	/** full constructor */
	public Product(String id, int category, String nameZh, String name, String nameDescribe, String yieldDescribe, Double yield, String title1, String title2, String minDeadline, String minMoney, String refundType, int isSell, int isNewProduct, int isRecommend, String productDescribe, String riskControl, String details, int isdelete, Date createtime, Date modifytime, String createuser, String modifyuser, int useTicket) {
		this.id = id;
		this.category = category;
		this.nameZh = nameZh;
		this.name = name;
		this.nameDescribe = nameDescribe;
		this.yieldDescribe = yieldDescribe;
		this.yield = yield;
		this.title1 = title1;
		this.title2 = title2;
		this.minDeadline = minDeadline;
		this.minMoney = minMoney;
		this.refundType = refundType;
		this.isSell = isSell;
		this.isNewProduct = isNewProduct;
		this.isRecommend = isRecommend;
		this.productDescribe = productDescribe;
		this.riskControl = riskControl;
		this.details = details;
		this.isdelete = isdelete;
		this.createtime = createtime;
		this.modifytime = modifytime;
		this.createuser = createuser;
		this.modifyuser = modifyuser;
		this.useTicket = useTicket;
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

	@Column(name = "category", nullable = false)
	public int getCategory() {
		return this.category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	@Column(name = "name_zh", nullable = false)
	public String getNameZh() {
		return this.nameZh;
	}

	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name_describe", length = 500)
	public String getNameDescribe() {
		return this.nameDescribe;
	}

	public void setNameDescribe(String nameDescribe) {
		this.nameDescribe = nameDescribe;
	}

	@Column(name = "yield_describe", length = 2000)
	public String getYieldDescribe() {
		return this.yieldDescribe;
	}

	public void setYieldDescribe(String yieldDescribe) {
		this.yieldDescribe = yieldDescribe;
	}

	@Column(name = "yield", nullable = false, precision = 22, scale = 0)
	public Double getYield() {
		return this.yield;
	}

	public void setYield(Double yield) {
		this.yield = yield;
	}

	@Column(name = "title1", length = 500)
	public String getTitle1() {
		return this.title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	@Column(name = "title2", length = 500)
	public String getTitle2() {
		return this.title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	@Column(name = "min_deadline", nullable = false, length = 30)
	public String getMinDeadline() {
		return this.minDeadline;
	}

	public void setMinDeadline(String minDeadline) {
		this.minDeadline = minDeadline;
	}

	@Column(name = "min_money", nullable = false, length = 20)
	public String getMinMoney() {
		return this.minMoney;
	}

	public void setMinMoney(String minMoney) {
		this.minMoney = minMoney;
	}

	@Column(name = "refund_type", length = 30)
	public String getRefundType() {
		return this.refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}

	@Column(name = "is_sell")
	public int getIsSell() {
		return this.isSell;
	}

	public void setIsSell(int isSell) {
		this.isSell = isSell;
	}

	@Column(name = "is_new_product")
	public int getIsNewProduct() {
		return this.isNewProduct;
	}

	public void setIsNewProduct(int isNewProduct) {
		this.isNewProduct = isNewProduct;
	}

	@Column(name = "is_recommend")
	public int getIsRecommend() {
		return this.isRecommend;
	}

	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}

	@Column(name = "product_describe", length = 16777215)
	public String getProductDescribe() {
		return this.productDescribe;
	}

	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}

	@Column(name = "risk_control", length = 16777215)
	public String getRiskControl() {
		return this.riskControl;
	}

	public void setRiskControl(String riskControl) {
		this.riskControl = riskControl;
	}

	@Column(name = "details", length = 16777215)
	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Column(name = "isdelete", nullable = false)
	public int getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "modifytime", length = 19)
	public Date getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	@Column(name = "createuser", length = 36)
	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	@Column(name = "modifyuser", length = 36)
	public String getModifyuser() {
		return this.modifyuser;
	}

	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}

	@Column(name = "use_ticket")
	public int getUseTicket() {
		return this.useTicket;
	}

	public void setUseTicket(int useTicket) {
		this.useTicket = useTicket;
	}

}