package org.duang.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * InvestProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "invest_product", catalog = "duang")
public class InvestProduct implements java.io.Serializable {

	// Fields

	private String id;
	private String nameZh;
	private String name;
	private String nameDescribe;
	private String yieldDescribe;
	private Float yield;
	private String chargeRatio;
	private String title1;
	private String title2;
	private String minDeadline;
	private String minMoney;
	private String refundType;
	private String isSell;
	private String isLottery;
	private String isRedEnvel;
	private String isNewProduct;
	private String isRecommend;
	private String productDescribe;
	private String riskControl;
	private String details;
	private Integer isdelete;
	private Timestamp createtime;
	private Timestamp modifytime;
	private String createuser;
	private String modifyuser;

	// Constructors

	/** default constructor */
	public InvestProduct() {
	}

	/** minimal constructor */
	public InvestProduct(String id, String nameZh, String name, Float yield, String minDeadline, String minMoney, Integer isdelete) {
		this.id = id;
		this.nameZh = nameZh;
		this.name = name;
		this.yield = yield;
		this.minDeadline = minDeadline;
		this.minMoney = minMoney;
		this.isdelete = isdelete;
	}

	/** full constructor */
	public InvestProduct(String id, String nameZh, String name, String nameDescribe, String yieldDescribe, Float yield, String chargeRatio, String title1, String title2, String minDeadline, String minMoney, String refundType, String isSell, String isLottery, String isRedEnvel, String isNewProduct, String isRecommend, String productDescribe, String riskControl, String details, Integer isdelete, Timestamp createtime, Timestamp modifytime, String createuser, String modifyuser) {
		this.id = id;
		this.nameZh = nameZh;
		this.name = name;
		this.nameDescribe = nameDescribe;
		this.yieldDescribe = yieldDescribe;
		this.yield = yield;
		this.chargeRatio = chargeRatio;
		this.title1 = title1;
		this.title2 = title2;
		this.minDeadline = minDeadline;
		this.minMoney = minMoney;
		this.refundType = refundType;
		this.isSell = isSell;
		this.isLottery = isLottery;
		this.isRedEnvel = isRedEnvel;
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

	@Column(name = "yield_describe", length = 1000)
	public String getYieldDescribe() {
		return this.yieldDescribe;
	}

	public void setYieldDescribe(String yieldDescribe) {
		this.yieldDescribe = yieldDescribe;
	}

	@Column(name = "yield", nullable = false, precision = 50, scale = 6)
	public Float getYield() {
		return this.yield;
	}

	public void setYield(Float yield) {
		this.yield = yield;
	}

	@Column(name = "charge_ratio")
	public String getChargeRatio() {
		return this.chargeRatio;
	}

	public void setChargeRatio(String chargeRatio) {
		this.chargeRatio = chargeRatio;
	}

	@Column(name = "title1")
	public String getTitle1() {
		return this.title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	@Column(name = "title2")
	public String getTitle2() {
		return this.title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	@Column(name = "min_deadline", nullable = false, length = 11)
	public String getMinDeadline() {
		return this.minDeadline;
	}

	public void setMinDeadline(String minDeadline) {
		this.minDeadline = minDeadline;
	}

	@Column(name = "min_money", nullable = false, length = 11)
	public String getMinMoney() {
		return this.minMoney;
	}

	public void setMinMoney(String minMoney) {
		this.minMoney = minMoney;
	}

	@Column(name = "refund_type", length = 11)
	public String getRefundType() {
		return this.refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}

	@Column(name = "is_sell", length = 11)
	public String getIsSell() {
		return this.isSell;
	}

	public void setIsSell(String isSell) {
		this.isSell = isSell;
	}

	@Column(name = "is_lottery", length = 11)
	public String getIsLottery() {
		return this.isLottery;
	}

	public void setIsLottery(String isLottery) {
		this.isLottery = isLottery;
	}

	@Column(name = "is_red_envel", length = 11)
	public String getIsRedEnvel() {
		return this.isRedEnvel;
	}

	public void setIsRedEnvel(String isRedEnvel) {
		this.isRedEnvel = isRedEnvel;
	}

	@Column(name = "is_new_product", length = 11)
	public String getIsNewProduct() {
		return this.isNewProduct;
	}

	public void setIsNewProduct(String isNewProduct) {
		this.isNewProduct = isNewProduct;
	}

	@Column(name = "is_recommend", length = 11)
	public String getIsRecommend() {
		return this.isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

	@Column(name = "product_describe", length = 2000)
	public String getProductDescribe() {
		return this.productDescribe;
	}

	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}

	@Column(name = "risk_control", length = 2000)
	public String getRiskControl() {
		return this.riskControl;
	}

	public void setRiskControl(String riskControl) {
		this.riskControl = riskControl;
	}

	@Column(name = "details", length = 2000)
	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Column(name = "isdelete", nullable = false)
	public Integer getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	@Column(name = "createtime", length = 19)
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "modifytime", length = 19)
	public Timestamp getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Timestamp modifytime) {
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

}